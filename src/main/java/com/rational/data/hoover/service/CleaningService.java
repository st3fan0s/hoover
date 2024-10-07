package com.rational.data.hoover.service;

import com.rational.data.hoover.dto.CleaningRequestDTO;
import com.rational.data.hoover.dto.CleaningResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class CleaningService {


    public CleaningResponseDTO handleCleaningRequest(CleaningRequestDTO cleaningRequestDTO) {

        CleaningResponseDTO responseDTO = new CleaningResponseDTO();

        return responseDTO;
    }
}
