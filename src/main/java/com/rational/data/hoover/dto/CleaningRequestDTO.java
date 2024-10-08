package com.rational.data.hoover.dto;

import javax.validation.constraints.*;
import java.util.List;

public class CleaningRequestDTO {

    @NotNull(message = "Room size is required.")
    @Size(min = 2, max = 2, message = "Room size must consist of exactly 2 values.")
    private List<@NotNull(message = "Room dimensions cannot be null.") @Min(1) Integer> roomSize;
    // List of 2 integers representing room dimensions [x, y]

    @NotNull(message = "Starting coordinates are required.")
    @Size(min = 2, max = 2, message = "Coordinates must consist of exactly 2 values.")
    private List<@NotNull(message = "Coordinate values cannot be null.") @Min(0) Integer> coords;
    // List of 2 integers representing the current position of the hoover [x, y]

    @NotNull(message = "Patches cannot be null.")
    @Size(min = 0, message = "Patches must be a list of coordinate pairs.")
    private List<@Size(min = 2, max = 2, message = "Each patch must have exactly 2 values.") List<@NotNull(message = "Patch coordinates cannot be null.") @Min(0) Integer>> patches;
    // List of Lists, each representing a patch position [x, y]

    @NotEmpty(message = "Instructions cannot be empty.")
    @Size(max = 100, message = "Instructions length should be less than or equal to 100 characters.")
    @Pattern(regexp = "^[NSWE]+$", message = "Only N, S, W, E ara allowed as given instructions")
    private String instructions;
    // String of movement instructions (N, S, E, W)

    // Getters and Setters
    public List<Integer> getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(List<Integer> roomSize) {
        this.roomSize = roomSize;
    }

    public List<Integer> getCoords() {
        return coords;
    }

    public void setCoords(List<Integer> coords) {
        this.coords = coords;
    }

    public List<List<Integer>> getPatches() {
        return patches;
    }

    public void setPatches(List<List<Integer>> patches) {
        this.patches = patches;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "\nCleaningRequestDTO {" +
                "\nroomSize = " + roomSize + "," +
                "\ncoords = " + coords + "," +
                "\npatches = " + patches + "," +
                "\ninstructions = '" + instructions + '\'' +
                "\n}";
    }
}
