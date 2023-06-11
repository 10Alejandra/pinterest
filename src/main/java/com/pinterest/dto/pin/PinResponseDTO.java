package com.pinterest.dto.pin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class PinResponseDTO implements Serializable {

    private Long id;
    private String title;
    private String description;
    private String urlImage;
    private String urlOwner;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updatedAt;
    private Long userId;

}
