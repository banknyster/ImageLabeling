package com.example.imagelabeling.Helper;

public class Label {
    private String name;
    private String confidence;

    public Label() {
    }

    public Label(String name, String confidence) {
        this.name = name;
        this.confidence = confidence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }
}
