package com.pinterest.service;

import com.pinterest.dto.comment.CommentRequestDTO;
import com.pinterest.dto.comment.CommentResponseDTO;
import com.pinterest.exception.ResourceNotFoundException;
import com.pinterest.mapper.CommentMapper;
import com.pinterest.model.Comment;
import com.pinterest.model.Pin;
import com.pinterest.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PinService pinService;
    private final CommentMapper commentMapper;
    private static final String NOT_FOUND_MESSAGE = "not found";

    public Comment getCommentById(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(() ->
                 new ResourceNotFoundException("Comment with id=[" + commentId + "] " + NOT_FOUND_MESSAGE));
    }

    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {
        Pin pin = pinService.getPinIdOrThrowException(commentRequestDTO.getPinId());

        if (commentRequestDTO.getParenId() == null) {
            Comment comment = createParentComment(commentRequestDTO, pin);
            return commentMapper.toCommentResponseDTO(comment);
        } else {
            Comment parentComment = getCommentById(commentRequestDTO.getParenId());
            Comment comment = createChildComment(commentRequestDTO, pin, parentComment);
            return commentMapper.toCommentResponseDTO(comment);
        }
    }

    public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO toCommentUpdate) {
        pinService.getPinIdOrThrowException(toCommentUpdate.getPinId());
        Comment commentToUpdate = getCommentById(commentId);
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
            Comment toParentComment = getCommentById(parentComment.getParent().getId());
            Comment comment = commentMapper.toComment(commentRequestDTO, pin, toParentComment);
            return commentRepository.save(comment);
        }

        Comment comment = commentMapper.toComment(commentRequestDTO, pin, parentComment);
        return commentRepository.save(comment);
    }

}
