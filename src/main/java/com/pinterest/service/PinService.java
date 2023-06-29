package com.pinterest.service;

import com.pinterest.dto.pin.PinListResponseDTO;
import com.pinterest.dto.pin.PinRequestDTO;
import com.pinterest.dto.pin.PinResponseDTO;
import com.pinterest.exception.ResourceNotAuthorizedException;
import com.pinterest.exception.ResourceNotFoundException;
import com.pinterest.exception.ResourceUploadException;
import com.pinterest.mapper.PinBoardMapper;
import com.pinterest.mapper.PinChildBoardMapper;
import com.pinterest.mapper.PinMapper;
import com.pinterest.model.Board;
import com.pinterest.model.ChildBoard;
import com.pinterest.model.Pin;
import com.pinterest.model.PinBoard;
import com.pinterest.model.PinChildBoard;
import com.pinterest.model.User;
import com.pinterest.repository.BoardRepository;
import com.pinterest.repository.ChildBoardRepository;
import com.pinterest.repository.PinBoardRepository;
import com.pinterest.repository.PinChildBoardRepository;
import com.pinterest.repository.PinRepository;
import com.pinterest.s3.S3Bucket;
import com.pinterest.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PinService {

    private final S3Service s3Service;
    private final S3Bucket s3Bucket;
    private final PinRepository pinRepository;
    private final BoardRepository boardRepository;
    private final PinBoardRepository pinBoardRepository;
    private final ChildBoardRepository childBoardRepository;
    private final PinChildBoardRepository pinChildBoardRepository;
    private final PinMapper pinMapper;
    private final PinBoardMapper pinBoardMapper;
    private final PinChildBoardMapper pinChildBoardMapper;
    private final UserService userService;
    private static final String NOT_FOUND_MESSAGE = " not found";

    public PinResponseDTO getPinById(Long pinId) {
        return pinMapper.toPinResponseDTO(getPinIdOrThrowException(pinId));
    }
    public Pin getPinIdOrThrowException (Long pinId) {
        return pinRepository.findById(pinId)
                .orElseThrow(() -> new ResourceNotFoundException("Pin with id=[" + pinId + "]"+NOT_FOUND_MESSAGE));
    }

    public PinListResponseDTO getAllPins(Pageable pageable) {
        Page<Pin> pinPage = pinRepository.findAll(pageable);
        return pinMapper.toPinListResponseDTO(pinPage);
    }

    @Transactional
    public PinResponseDTO createPin(PinRequestDTO pinRequestDTO, MultipartFile file) {
        User user = userService.getUserByIdOrThrowException(pinRequestDTO.getUserId());
        Board board = validateBoard(pinRequestDTO.getBoardId(), user.getId());
        ChildBoard childBoard = validateChildBoard(pinRequestDTO.getChildBoardId(), board.getId());

        String pinImageId = UUID.randomUUID().toString();
        try {
            s3Service.putObject(
                    s3Bucket.getPinterest(),
                    "pins/" + pinImageId,
                    file.getBytes()
            );
        } catch (IOException e) {
            throw new ResourceUploadException("failed to upload pin image", e);
        }

        Pin pin = pinRepository.save(pinMapper.toPin(pinRequestDTO, user, pinImageId));

        Long relationshipKey;
        if (Objects.nonNull(childBoard)) {
             PinChildBoard pinChildBoard = pinChildBoardRepository.save(pinChildBoardMapper.toPinChildBoard(pin, childBoard));
             relationshipKey = pinChildBoard.getId();
        } else {
            PinBoard pinBoard = pinBoardRepository.save(pinBoardMapper.toPinBoard(pin, board));
            relationshipKey = pinBoard.getId();
        }

        pinRepository.updateRelationshipKeyField(relationshipKey, pin.getId());

        return pinMapper.toPinResponseDTO(pin);
    }

    public void deletePinById(Long pinId) {
        getPinById(pinId);
        pinRepository.deleteById(pinId);
    }

    private Board validateBoard(Long boardId, Long userId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new
                ResourceNotFoundException("Board with id=[" + boardId + "]"+NOT_FOUND_MESSAGE));

        if (!Objects.equals(board.getUser().getId(), userId)) {
            throw new ResourceNotAuthorizedException("You do not have permissions on this board");
        }

        return board;
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
