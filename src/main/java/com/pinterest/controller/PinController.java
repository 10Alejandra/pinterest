package com.pinterest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinterest.dto.pin.PinListResponseDTO;
import com.pinterest.dto.pin.PinRequestDTO;
import com.pinterest.dto.pin.PinResponseDTO;
import com.pinterest.service.PinService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @PostMapping(
            value = "",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }
    )
    public ResponseEntity<PinResponseDTO> createPin(
            @RequestPart("pin") String pin,
            @RequestPart("file") MultipartFile file
    ) {
        PinRequestDTO pinRequestDTO = getJson(pin);
        PinResponseDTO pinCreated = pinService.createPin(pinRequestDTO, file);
        return new ResponseEntity<>(pinCreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/{pinId}")
    public ResponseEntity<String> deletePinById(@PathVariable("pinId") Long pinId) {
        pinService.deletePinById(pinId);
        return ResponseEntity.ok("Pin removed successfully");
    }

    private PinRequestDTO getJson(String pin) {
        PinRequestDTO pinRequestDTO = new PinRequestDTO();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            pinRequestDTO = objectMapper.readValue(pin, PinRequestDTO.class);
        } catch (IOException err) {
            System.out.println(err);
        }

        return pinRequestDTO;
    }

}
