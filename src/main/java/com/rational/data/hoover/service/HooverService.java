package com.rational.data.hoover.service;

import com.rational.data.hoover.dto.CleaningRequestDTO;
import com.rational.data.hoover.dto.CleaningResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HooverService {

    private final Logger logger = LoggerFactory.getLogger(HooverService.class);

    public CleaningResponseDTO executeCleaningRequest(CleaningRequestDTO cleaningRequestDTO) {

        int roomSizeX = cleaningRequestDTO.getRoomSize().get(0);
        int roomSizeY = cleaningRequestDTO.getRoomSize().get(1);

        List<Integer> coords = cleaningRequestDTO.getCoords();

        if (coords.get(0) >= roomSizeX)
            throw new ValidationException("Starting position in the X Axis can not be equal or greater than room's size in the X Axis");

        if (coords.get(1) >= roomSizeY)
            throw new ValidationException("Starting position in the Y Axis can not be equal or greater than room's size in the Y Axis");

        int patchesCleaned = 0;
        Set<List<Integer>> patchesSet = new HashSet<>(cleaningRequestDTO.getPatches());
        String instructions = cleaningRequestDTO.getInstructions();

        if (patchesSet.contains(coords)) {
            patchesCleaned++;
            patchesSet.remove(coords);  // Remove the initial patch if the hoover starts there and it is dirty
        }

        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'N':
                    if (coords.get(1) < roomSizeY - 1) {
                        coords.set(1, coords.get(1) + 1);
                    } else {
                        logger.debug("Already at the North side of the room, no movement to be made");
                    }
                    break;
                case 'S':
                    if (coords.get(1) > 0) {
                        coords.set(1, coords.get(1) - 1);
                    } else {
                        logger.debug("Already at the South side of the room, no movement to be made");
                    }
                    break;
                case 'E':
                    if (coords.get(0) < roomSizeX - 1) {
                        coords.set(0, coords.get(0) + 1);
                    } else {
                        logger.debug("Already at the East side of the room, no movement to be made");
                    }
                    break;
                case 'W':
                    if (coords.get(0) > 0) {
                        coords.set(0, coords.get(0) - 1);
                    } else {
                        logger.debug("Already at the West side of the room, no movement to be made");
                    }
                    break;
            }

            if (patchesSet.contains(coords)) {
                patchesCleaned++;
                patchesSet.remove(coords);  // Remove the cleaned patch
            }
        }

        CleaningResponseDTO responseDTO = new CleaningResponseDTO();
        responseDTO.setCoords(coords);
        responseDTO.setPatches(patchesCleaned);
        return responseDTO;
    }
}
