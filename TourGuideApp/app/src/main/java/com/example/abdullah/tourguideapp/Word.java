package com.example.abdullah.tourguideapp;

public class Word {

    private int nameID;
    private int locationID;
    private int imageID;
    private static final int NO_IMAGE_PROVIDED = 0;

    public Word(int nameID, int locationID, int imageResourceID) {
        this.nameID = nameID;
        this.locationID = locationID;
        imageID =imageResourceID;

    }
    public Word(int nameID, int locationID) {
        this.nameID = nameID;
        this.locationID = locationID;


    }

    public int getNameID() {

        return nameID;
    }

    public int getLocationID() {

        return locationID;
    }

    public int getImageID() {
        return imageID;
    }

     public boolean hasImage() {
        return imageID != NO_IMAGE_PROVIDED;
    }

}