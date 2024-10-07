package com.rational.data.hoover.controller;

import com.rational.data.hoover.dto.CleaningRequestDTO;
import com.rational.data.hoover.dto.CleaningResponseDTO;
import com.rational.data.hoover.service.CleaningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hoover")
public class HooverController {

    private final CleaningService cleaningService;

    @Autowired
    public HooverController(CleaningService cleaningService) {
        this.cleaningService = cleaningService;
    }

    @PutMapping("/execute")
    public ResponseEntity<CleaningResponseDTO> handleCleaningRequest(@RequestBody CleaningRequestDTO cleaningRequestDTO) {

        try {
            // Return the response along with HTTP 200 OK
            return new ResponseEntity<>(cleaningService.handleCleaningRequest(cleaningRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
