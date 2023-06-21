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
    private static final String NOT_FOUND_MESSAGE = "not found";

    public Comment getCommentById(Long commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()){
            throw new ResourceNotFoundException("Comment with id=[" + commentId + "] " + NOT_FOUND_MESSAGE);
        }
        return comment.get();
    }

    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {
        Optional<Pin> pin = pinRepository.findById(commentRequestDTO.getPinId());

        if (pin.isEmpty()) {
            throw new ResourceNotFoundException("Pin with id=[" + commentRequestDTO.getPinId() + "] " + NOT_FOUND_MESSAGE);
        }

        if (commentRequestDTO.getParenId() == null) {
            Comment comment = createParentComment(commentRequestDTO, pin.get());
            return commentMapper.toCommentResponseDTO(comment);
        } else {
            Comment parentComment = commentRepository.findById(commentRequestDTO.getParenId()).orElseThrow(() ->
                    new ResourceNotFoundException("Parent comment with id=[" + commentRequestDTO.getParenId() + "] " + NOT_FOUND_MESSAGE));
            Comment comment = createChildComment(commentRequestDTO, pin.get(), parentComment);
            return commentMapper.toCommentResponseDTO(comment);
        }
    }

    public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO toCommentUpdate) {
        Optional<Pin> pin = pinRepository.findById(toCommentUpdate.getPinId());
        Comment commentToUpdate = getCommentById(commentId);

        if (pin.isEmpty()) {
            throw new ResourceNotFoundException("Pin with id=[" + toCommentUpdate.getPinId() + "] " + NOT_FOUND_MESSAGE);
        }

        commentToUpdate.setContent(toCommentUpdate.getContent());
        Comment updatedComment = commentRepository.save(commentToUpdate);
        return commentMapper.toCommentResponseDTO(updatedComment);
    }

    public void deleteCommentById(Long commentId) {
        getCommentById(commentId);
        commentRepository.deleteById(commentId);
    }

    private Comment createParentComment(CommentRequestDTO commentRequestDTO, Pin pin ) {
        Comment comment = commentMapper.toComment(commentRequestDTO, pin, null);
        return commentRepository.save(comment);
    }

    private Comment createChildComment(CommentRequestDTO commentRequestDTO, Pin pin, Comment parentComment) {
        if(parentComment.getParent() != null) {
            Comment toParentComment = commentRepository.findById(parentComment.getParent().getId()).orElseThrow(() ->
                    new ResourceNotFoundException("Parent comment with id=[" + commentRequestDTO.getParenId() + "] " + NOT_FOUND_MESSAGE));
            Comment comment = commentMapper.toComment(commentRequestDTO, pin, toParentComment);
            return commentRepository.save(comment);
        }

        Comment comment = commentMapper.toComment(commentRequestDTO, pin, parentComment);
        return commentRepository.save(comment);
    }

}
