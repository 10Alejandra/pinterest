package com.pinterest.mapper;

import com.pinterest.dto.comment.CommentRequestDTO;
import com.pinterest.dto.comment.CommentResponseDTO;
import com.pinterest.model.Comment;
import com.pinterest.model.Pin;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CommentMapper {

    public CommentResponseDTO toCommentResponseDTO(Comment comment) {
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt().toString())
                .updatedAt(comment.getUpdateAt().toString())
                .parentId(Objects.nonNull(comment.getParent()) ? comment.getParent().getId() : null)
                .pinId(comment.getPin().getId())
                .build();
    }

    public Comment toComment(CommentRequestDTO commentRequestDTO, Pin pin, Comment comment) {
        return Comment.builder()
                .content(commentRequestDTO.getContent())
                .pin(pin)
                .parent(Objects.nonNull(comment) ? comment : null)
                .build();
    }

}
