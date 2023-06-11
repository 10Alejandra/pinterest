package com.pinterest.dto.pin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class PinListResponseDTO implements Serializable {

    private List<PinResponseDTO> pins;
    private int currentPage;
    private long totalItems;
    private int totalPages;

}
