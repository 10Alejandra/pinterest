package com.pinterest.dto.pin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PinRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8973568745552374264L;

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
