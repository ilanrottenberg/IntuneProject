package com.example.intune;

public class Song {
    private String name;
    private double rate;
    private String style;
    private String composer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return  rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public Song(String name, int rate, String style, String composer){
        this.name=name;
        this.rate=rate;
        this.style=style;
        this.composer=composer;
    }
}
