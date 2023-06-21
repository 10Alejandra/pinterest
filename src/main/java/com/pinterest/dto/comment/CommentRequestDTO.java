package com.pinterest.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class CommentRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5203756609336064352L;

    private String content;

    private Long parenId;

    @NonNull
    private Long pinId;

}
