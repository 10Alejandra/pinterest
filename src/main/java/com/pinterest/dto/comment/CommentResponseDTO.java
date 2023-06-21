package com.pinterest.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class CommentResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5224446419012634164L;

    private Long id;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updatedAt;

    private Long parentId;

    private Long pinId;

}
