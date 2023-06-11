package com.pinterest.mapper;

import com.pinterest.dto.pin.PinListResponseDTO;
import com.pinterest.dto.pin.PinRequestDTO;
import com.pinterest.dto.pin.PinResponseDTO;
import com.pinterest.model.Pin;
import com.pinterest.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PinMapper {

    public Pin toPin(PinRequestDTO pinRequestDTO, User user) {
        return Pin.builder()
                .title(pinRequestDTO.getTitle())
                .description(pinRequestDTO.getDescription())
                .urlImage(pinRequestDTO.getUrlImage())
                .urlOwner(pinRequestDTO.getUrlOwner())
                .user(user)
                .build();
    }

    public PinResponseDTO toPinResponseDTO(Pin pin) {
        return PinResponseDTO.builder()
                .id(pin.getId())
                .title(pin.getTitle())
                .description(pin.getDescription())
                .urlImage(pin.getUrlImage())
                .urlOwner(pin.getUrlOwner())
                .createdAt(pin.getCreatedAt().toString())
                .updatedAt(pin.getUpdatedAt().toString())
                .userId(pin.getUser().getId())
                .build();
    }

    public PinListResponseDTO toPinListResponseDTO(Page<Pin> pinPage) {
        List<PinResponseDTO> pinResponseDTOList = pinPage.getContent()
                .stream()
                .map(this::toPinResponseDTO)
                .toList();

        return PinListResponseDTO.builder()
                .pins(pinResponseDTOList)
                .currentPage(pinPage.getNumber())
                .totalItems(pinPage.getTotalElements())
                .totalPages(pinPage.getTotalPages())
                .build();
    }

}
