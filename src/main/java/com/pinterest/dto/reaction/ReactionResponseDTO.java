package com.pinterest.dto.reaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ReactionResponseDTO implements Serializable {

    private Long id;
    private String type;
    private Long userId;
    private Long commentId;

}
