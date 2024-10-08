package com.rational.data.hoover.controller;

import com.rational.data.hoover.dto.CleaningRequestDTO;
import com.rational.data.hoover.dto.CleaningResponseDTO;
import com.rational.data.hoover.service.HooverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/hoover")
@Validated
public class HooverController {

    private final Logger logger = LoggerFactory.getLogger(HooverController.class);

    private final HooverService hooverService;

    @Autowired
    public HooverController(HooverService hooverService) {
        this.hooverService = hooverService;
    }

    @PutMapping("/execute")
    public ResponseEntity<CleaningResponseDTO> executeCleaningRequest(@Valid @RequestBody CleaningRequestDTO cleaningRequestDTO) {
        logger.info("Received the following input {}", cleaningRequestDTO);

        try {
            CleaningResponseDTO cleaningResponseDTO = hooverService.executeCleaningRequest(cleaningRequestDTO);
            logger.info("Responded with {}", cleaningResponseDTO);
            // Return the response along with HTTP 200 OK
            return new ResponseEntity<>(cleaningResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error during cleaning request", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
