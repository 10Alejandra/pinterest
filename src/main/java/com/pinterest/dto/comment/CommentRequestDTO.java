package com.pinterest.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class CommentRequestDTO implements Serializable {

    private String content;
    private Long parenId;
    @NonNull
    private Long pinId;

}
