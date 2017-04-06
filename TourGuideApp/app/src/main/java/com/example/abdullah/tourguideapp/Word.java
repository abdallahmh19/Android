package com.example.abdullah.tourguideapp;

public class Word {

    private String name;
    private String location;
    private int imageID;
    private static final int NO_IMAGE_PROVIDED = 0;

    public Word(String name, String location, int imageResourceID) {
        this.name = name;
        this.location = location;
        imageID =imageResourceID;

    }
    public Word(String name, String location) {
        this.name = name;
        this.location = location;


    }

    public String getName() {

        return name;
    }

    public String getLocation() {

        return location;
    }

    public int getImageID() {
        return imageID;
    }

     public boolean hasImage() {
        return imageID != NO_IMAGE_PROVIDED;
    }

}