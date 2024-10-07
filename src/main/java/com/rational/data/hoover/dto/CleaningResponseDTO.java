package com.rational.data.hoover.dto;

import java.util.List;

public class CleaningResponseDTO {

    private List<Integer> coords;  // Final coordinates of the hoover
    private int patches;           // Number of cleaned patches

    // Getters and Setters
    public List<Integer> getCoords() {
        return coords;
    }

    public void setCoords(List<Integer> coords) {
        this.coords = coords;
    }

    public int getPatches() {
        return patches;
    }

    public void setPatches(int patches) {
        this.patches = patches;
    }

    @Override
    public String toString() {
        return "CleaningResponseDTO{" +
                "coords=" + coords +
                ", patches=" + patches +
                '}';
    }
}
