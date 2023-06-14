package com.pinterest.service;

import com.pinterest.dto.comment.CommentRequestDTO;
import com.pinterest.dto.comment.CommentResponseDTO;
import com.pinterest.exception.ResourceNotFoundException;
import com.pinterest.mapper.CommentMapper;
import com.pinterest.model.Comment;
import com.pinterest.model.Pin;
import com.pinterest.repository.CommentRepository;
import com.pinterest.repository.PinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PinRepository pinRepository;
    private final CommentMapper commentMapper;

    public CommentResponseDTO getCommentById(Long commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()){
            throw new ResourceNotFoundException("Comment with id=[" + commentId + "] not found");
        }
        return commentMapper.toCommentResponseDTO(comment.get());

    }
    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {
        Optional<Pin> pin = pinRepository.findById(commentRequestDTO.getPinId());

        if(pin.isEmpty()) {
            throw new ResourceNotFoundException("Pin with id=[" + commentRequestDTO.getPinId() + "] not found");
        }
        if (commentRequestDTO.getParenId() == null) {
            Comment comment = createParentComment(commentRequestDTO, pin.get());
            return commentMapper.toCommentResponseDTO(comment);
        }else {
            Comment parentComment = commentRepository.findById(commentRequestDTO.getParenId()).orElseThrow(() -> new
                    ResourceNotFoundException("Parent comment with id=[" + commentRequestDTO.getParenId() + "] not found"));
            Comment comment = createChildComment(commentRequestDTO, pin.get(), parentComment);
            return commentMapper.toCommentResponseDTO(comment);
        }

    }
    private Comment createParentComment(CommentRequestDTO commentRequestDTO, Pin pin ) {
        Comment comment = commentMapper.toComment(commentRequestDTO, pin, null);
        return commentRepository.save(comment);
    }
    private Comment createChildComment(CommentRequestDTO commentRequestDTO, Pin pin, Comment parentComment) {
        Comment comment = commentMapper.toComment(commentRequestDTO, pin, parentComment);
        return commentRepository.save(comment);
    }
}
