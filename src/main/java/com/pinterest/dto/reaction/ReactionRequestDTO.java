package com.pinterest.dto.reaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class ReactionRequestDTO implements Serializable {

    private String type;
    @NonNull
    private Long userId;
    @NonNull
    private Long commentId;
}
