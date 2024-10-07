package com.rational.data.hoover.dto;

import java.util.List;

public class CleaningRequestDTO {

    private List<Integer> roomSize;      // List of 2 integers representing room dimensions [x, y]
    private List<Integer> coords;        // List of 2 integers representing the current position of the hoover [x, y]
    private List<List<Integer>> patches; // List of Lists, each representing a patch position [x, y]
    private String instructions;         // String of movement instructions (N, S, E, W)

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
        return "CleaningRequestDTO{" +
                "roomSize=" + roomSize +
                ", coords=" + coords +
                ", patches=" + patches +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
