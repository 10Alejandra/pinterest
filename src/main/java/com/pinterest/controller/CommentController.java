package com.pinterest.controller;

import com.pinterest.dto.comment.CommentRequestDTO;
import com.pinterest.dto.comment.CommentResponseDTO;
import com.pinterest.model.Comment;
import com.pinterest.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("commentId") Long commentId) {
        Comment commentResponseDTO = commentService.getCommentById(commentId);
        return new ResponseEntity<>(commentResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO commentRequestDTO) {
       CommentResponseDTO commentCreated = commentService.createComment(commentRequestDTO);
       return new ResponseEntity<>(commentCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long commentId,  @RequestBody CommentRequestDTO commentUpdate ) {
        CommentResponseDTO updateComment = commentService.updateComment(commentId, commentUpdate);
        return new ResponseEntity<>(updateComment, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("commentId") Long commentId) {
        commentService.deleteCommentById(commentId);
        return ResponseEntity.ok("Comment removed successfully");
    }



}
