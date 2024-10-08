package com.rational.data.hoover.controller;

import com.rational.data.hoover.dto.CleaningRequestDTO;
import com.rational.data.hoover.dto.CleaningResponseDTO;
import com.rational.data.hoover.service.HooverService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HooverController.class)
public class HooverControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HooverService hooverService;

    @Test
    public void testCleanRoomInvalidInput() throws Exception {
        // Arrange: Create invalid JSON input (missing room size)
        String invalidRequestJson = "{"
                + "\"coords\": [1, 2],"
                + "\"patches\": [[1, 0], [2, 2], [2, 3]],"
                + "\"instructions\": \"NNESEESWNWW\""
                + "}";

        // Act & Assert: Send a POST request and expect a validation error
        mockMvc.perform(put("/hoover/execute")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequestJson))
                .andExpect(status().isBadRequest())  // Expect HTTP 400 Bad Request
                .andExpect(jsonPath("$.roomSize").exists())  // Check that the error for "roomSize" exists
                .andExpect(jsonPath("$.roomSize").value("Room size is required."));  // Validate error message
    }

    @Test
    public void testCleanRoomValidInput() throws Exception {
        // Arrange: Create valid JSON input
        String validRequestJson = "{"
                + "\"roomSize\": [5, 5],"
                + "\"coords\": [1, 2],"
                + "\"patches\": [[1, 0], [2, 2], [2, 3]],"
                + "\"instructions\": \"NNESEESWNWW\""
                + "}";

        // Arrange: Mock service response
        CleaningResponseDTO mockResponse = new CleaningResponseDTO();
        mockResponse.setCoords(Arrays.asList(1, 3));
        mockResponse.setPatches(1);

        when(hooverService.executeCleaningRequest(any(CleaningRequestDTO.class))).thenReturn(mockResponse);

        // Act & Assert: Send a POST request and expect a successful response
        mockMvc.perform(put("/hoover/execute")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestJson))
                .andExpect(status().isOk())  // Expect HTTP 200 OK
                .andExpect(jsonPath("$.coords").isArray())
                .andExpect(jsonPath("$.coords[0]").value(1))
                .andExpect(jsonPath("$.coords[1]").value(3))
                .andExpect(jsonPath("$.patches").value(1));

        verify(hooverService, times(1)).executeCleaningRequest(any(CleaningRequestDTO.class));
    }
}