package com.pinterest.dto.pin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class PinRequestDTO implements Serializable {

    private String title;
    private String description;
    @NonNull
    private String urlImage;
    private String urlOwner;
    @NonNull
    private Long userId;
    @NonNull
    private Long boardId;
    private Long childBoardId;

}
