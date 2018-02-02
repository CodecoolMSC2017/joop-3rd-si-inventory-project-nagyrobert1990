package com.codecool;

public class CDProduct extends Product {

    private int numOfTracks;

    public CDProduct(String name, int price, int numOfTracks) {
        super(name, price);
        this.numOfTracks = numOfTracks;
    }

    @Override
    public int getSize() {
        return numOfTracks;
    }
}
