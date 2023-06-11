package com.pinterest.controller;

import com.pinterest.dto.pin.PinListResponseDTO;
import com.pinterest.dto.pin.PinRequestDTO;
import com.pinterest.dto.pin.PinResponseDTO;
import com.pinterest.service.PinService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pins")
@RequiredArgsConstructor
public class PinController {

    private final PinService pinService;

    @GetMapping("/{pinId}")
    public ResponseEntity<PinResponseDTO> getPinById(@PathVariable("pinId") Long pinId) {
        PinResponseDTO pinResponseDTO = pinService.getPinById(pinId);
        return new ResponseEntity<>(pinResponseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PinListResponseDTO> getAllPins(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PinListResponseDTO pinListResponseDTO = pinService.getAllPins(pageable);
        return new ResponseEntity<>(pinListResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PinResponseDTO> createPin(@RequestBody PinRequestDTO pinRequestDTO) {
        PinResponseDTO pinCreated = pinService.createPin(pinRequestDTO);
        return new ResponseEntity<>(pinCreated, HttpStatus.CREATED);
    }

}
