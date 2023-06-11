package com.pinterest.service;

import com.pinterest.dto.pin.PinListResponseDTO;
import com.pinterest.dto.pin.PinRequestDTO;
import com.pinterest.dto.pin.PinResponseDTO;
import com.pinterest.exception.ResourceNotAuthorizedException;
import com.pinterest.exception.ResourceNotFoundException;
import com.pinterest.mapper.PinBoardMapper;
import com.pinterest.mapper.PinChildBoardMapper;
import com.pinterest.mapper.PinMapper;
import com.pinterest.model.Board;
import com.pinterest.model.ChildBoard;
import com.pinterest.model.Pin;
import com.pinterest.model.User;
import com.pinterest.repository.BoardRepository;
import com.pinterest.repository.ChildBoardRepository;
import com.pinterest.repository.PinBoardRepository;
import com.pinterest.repository.PinChildBoardRepository;
import com.pinterest.repository.PinRepository;
import com.pinterest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PinService {

    private final PinRepository pinRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final PinBoardRepository pinBoardRepository;
    private final ChildBoardRepository childBoardRepository;
    private final PinChildBoardRepository pinChildBoardRepository;
    private final PinMapper pinMapper;
    private final PinBoardMapper pinBoardMapper;
    private final PinChildBoardMapper pinChildBoardMapper;

    public PinResponseDTO getPinById(Long pinId) {
        Optional<Pin> pin = pinRepository.findById(pinId);
        if (pin.isEmpty()) {
            throw new ResourceNotFoundException("Pin with id=[" + pinId + "] not found");
        }
        return pinMapper.toPinResponseDTO(pin.get());
    }

    public PinListResponseDTO getAllPins(Pageable pageable) {
        Page<Pin> pinPage = pinRepository.findAll(pageable);
        return pinMapper.toPinListResponseDTO(pinPage);
    }

    @Transactional
    public PinResponseDTO createPin(PinRequestDTO pinRequestDTO) {
        Optional<User> user = userRepository.findById(pinRequestDTO.getUserId());
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User with id=[" + pinRequestDTO.getUserId() + "] not found");
        }

        Board board = validateBoard(pinRequestDTO.getBoardId(), user.get().getId());
        ChildBoard childBoard = validateChildBoard(pinRequestDTO.getChildBoardId(), board.getId());

        Pin pin = pinRepository.save(pinMapper.toPin(pinRequestDTO, user.get()));

        if (Objects.nonNull(childBoard)) {
             pinChildBoardRepository.save(pinChildBoardMapper.toPinChildBoard(pin, childBoard));
        } else {
            pinBoardRepository.save(pinBoardMapper.toPinBoard(pin, board));
        }

        return pinMapper.toPinResponseDTO(pin);
    }

    private Board validateBoard(Long boardId, Long userId) {
        Optional<Board> board = boardRepository.findById(boardId);
        if (board.isEmpty()) {
            throw new ResourceNotFoundException("Board with id=[" + boardId + "] not found");
        }

        if (!Objects.equals(board.get().getUser().getId(), userId)) {
            throw new ResourceNotAuthorizedException("You do not have permissions on this board");
        }

        return board.get();
    }

    private ChildBoard validateChildBoard(Long childBoardId, Long boardId) {
        if (!Objects.isNull(childBoardId)) {
            Optional<ChildBoard> childBoard = childBoardRepository.findById(childBoardId);
            if (childBoard.isPresent()) {
                if (!Objects.equals(childBoard.get().getBoard().getId(), boardId)) {
                    throw new ResourceNotAuthorizedException("You do not have permissions on this sub board");
                } else {
                    return childBoard.get();
                }
            }
        }
        return null;
    }

}
