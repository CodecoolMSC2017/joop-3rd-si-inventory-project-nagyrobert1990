package com.codecool;

public class BookProduct extends Product {

    private int numOfPages;

    public BookProduct(String name, int price, int numOfPages) {
        super(name, price);
        this.numOfPages = numOfPages;
    }

    @Override
    public int getSize() {
        return numOfPages;
    }
}
