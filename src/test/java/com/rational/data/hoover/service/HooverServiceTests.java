package com.rational.data.hoover.service;

import com.rational.data.hoover.dto.CleaningRequestDTO;
import com.rational.data.hoover.dto.CleaningResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class HooverServiceTests {

    private HooverService hooverService;

    @BeforeEach
    public void setUp() {
        hooverService = new HooverService();
    }

    @Test
    public void testCleanRoomValidInput() {
        // Arrange: Valid room size, coordinates, patches, and instructions
        CleaningRequestDTO requestDTO = new CleaningRequestDTO();
        requestDTO.setRoomSize(Arrays.asList(5, 5));
        requestDTO.setCoords(Arrays.asList(1, 2));
        requestDTO.setPatches(Arrays.asList(Arrays.asList(1, 0), Arrays.asList(2, 2), Arrays.asList(2, 3)));
        requestDTO.setInstructions("NNESEESWNWW");

        // Act: Call the executeCleaningRequest method
        CleaningResponseDTO responseDTO = hooverService.executeCleaningRequest(requestDTO);

        // Assert: Check the final coordinates and cleaned patches count
        assertEquals(Arrays.asList(1, 3), responseDTO.getCoords());
        assertEquals(1, responseDTO.getPatches());
    }

    @Test
    public void testCleanRoomOutOfBoundsInstructions() {
        // Arrange: Valid room size, coordinates, patches, and instructions that go out of bounds
        CleaningRequestDTO requestDTO = new CleaningRequestDTO();
        requestDTO.setRoomSize(Arrays.asList(5, 5));
        requestDTO.setCoords(Arrays.asList(0, 0));
        requestDTO.setPatches(Arrays.asList(Arrays.asList(0, 0), Arrays.asList(1, 1)));
        requestDTO.setInstructions("WWSSEEN");

        // Act: Call the executeCleaningRequest method
        CleaningResponseDTO responseDTO = hooverService.executeCleaningRequest(requestDTO);

        // Assert: Final coordinates should be within the room bounds
        assertEquals(Arrays.asList(2, 1), responseDTO.getCoords());
        assertEquals(1, responseDTO.getPatches());
    }

    @Test
    public void testCleanRoomNoPatchesCleaned() {
        // Arrange: Room with no dirt patches in the path of the hoover
        CleaningRequestDTO requestDTO = new CleaningRequestDTO();
        requestDTO.setRoomSize(Arrays.asList(5, 5));
        requestDTO.setCoords(Arrays.asList(1, 2));
        requestDTO.setPatches(Arrays.asList(Arrays.asList(0, 0), Arrays.asList(4, 4)));
        requestDTO.setInstructions("NNESEESWNWW");

        // Act: Call the executeCleaningRequest method
        CleaningResponseDTO responseDTO = hooverService.executeCleaningRequest(requestDTO);

        // Assert: Check that no patches were cleaned
        assertEquals(Arrays.asList(1, 3), responseDTO.getCoords());
        assertEquals(0, responseDTO.getPatches());
    }

    @Test
    public void testCleanRoomAllPatchesCleaned() {
        // Arrange: Room where all patches are cleaned by the hoover
        CleaningRequestDTO requestDTO = new CleaningRequestDTO();
        requestDTO.setRoomSize(Arrays.asList(5, 5));
        requestDTO.setCoords(Arrays.asList(1, 2));
        requestDTO.setPatches(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 2)));
        requestDTO.setInstructions("ESE");

        // Act: Call the executeCleaningRequest method
        CleaningResponseDTO responseDTO = hooverService.executeCleaningRequest(requestDTO);

        // Assert: Check that both patches were cleaned
        assertEquals(Arrays.asList(3, 1), responseDTO.getCoords());
        assertEquals(2, responseDTO.getPatches());
    }

    @Test
    public void testCleanRoomInvalidStartCoordinates_1() {
        // Arrange: Invalid starting coordinates (out of room bounds)
        CleaningRequestDTO requestDTO = new CleaningRequestDTO();
        requestDTO.setRoomSize(Arrays.asList(5, 5));
        requestDTO.setCoords(Arrays.asList(5, 4));  // Invalid starting position
        requestDTO.setPatches(Arrays.asList(Arrays.asList(1, 0), Arrays.asList(2, 2)));
        requestDTO.setInstructions("NNESEESWNWW");

        // Act & Assert: Should throw IllegalArgumentException
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            hooverService.executeCleaningRequest(requestDTO);
        });

        assertEquals("Starting position in the X Axis can not be equal or greater than room's size in the X Axis", exception.getMessage());
    }

    @Test
    public void testCleanRoomInvalidStartCoordinates_2() {
        // Arrange: Invalid starting coordinates (out of room bounds)
        CleaningRequestDTO requestDTO = new CleaningRequestDTO();
        requestDTO.setRoomSize(Arrays.asList(5, 5));
        requestDTO.setCoords(Arrays.asList(4, 5));  // Invalid starting position
        requestDTO.setPatches(Arrays.asList(Arrays.asList(1, 0), Arrays.asList(2, 2)));
        requestDTO.setInstructions("NNESEESWNWW");

        // Act & Assert: Should throw IllegalArgumentException
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            hooverService.executeCleaningRequest(requestDTO);
        });

        assertEquals("Starting position in the Y Axis can not be equal or greater than room's size in the Y Axis", exception.getMessage());
    }

    @Test
    public void testCleanRoomWithDuplicatePatches() {
        // Arrange: Room with duplicate patches (set should remove duplicates)
        CleaningRequestDTO requestDTO = new CleaningRequestDTO();
        requestDTO.setRoomSize(Arrays.asList(5, 5));
        requestDTO.setCoords(Arrays.asList(1, 2));
        requestDTO.setPatches(Arrays.asList(
                Arrays.asList(1, 0), Arrays.asList(2, 2), Arrays.asList(2, 3), Arrays.asList(2, 2)));  // Duplicate patches
        requestDTO.setInstructions("NNESEESWNWW");

        // Act: Call the executeCleaningRequest method
        CleaningResponseDTO responseDTO = hooverService.executeCleaningRequest(requestDTO);

        // Assert: Final coordinates and number of cleaned patches should be correct
        assertEquals(Arrays.asList(1, 3), responseDTO.getCoords());
        assertEquals(1, responseDTO.getPatches());
    }
}
