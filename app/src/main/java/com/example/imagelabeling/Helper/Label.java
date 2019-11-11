package com.example.imagelabeling.Helper;

public class Label {
    private String name;
    private double confidence;

    public Label() {
    }

    public Label(String name, float confidence) {
        this.name = name;
        this.confidence = confidence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
