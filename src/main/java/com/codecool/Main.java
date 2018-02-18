package com.codecool;

public class Main {

    public static void main(String[] args) {

        StoreManager storeManager = new StoreManager();
        PersistentStore ps = new PersistentStore();
        storeManager.addStorage(ps);
        storeManager.addCDProduct("Metallica", 14, 12);
        storeManager.addBookProduct("Silence of the Lambs", 11, 100);
        storeManager.addBookProduct("Harry Potter and the Philosopher's Stone", 16, 250);
        storeManager.addCDProduct("Hans Zimmer", 17, 20);
        String listOfEverything = storeManager.listProducts();
        int totalPrice = storeManager.getTotalProductPrice();

        CsvStore csvs = new CsvStore();
        storeManager.addStorage(csvs);
        storeManager.addCDProduct("Metallica", 14, 12);
        storeManager.addBookProduct("Silence of the Lambs", 11, 100);
        storeManager.addBookProduct("Harry Potter and the Philosopher's Stone", 16, 250);
        storeManager.addCDProduct("Hans Zimmer", 17, 20);
        if (listOfEverything.equals(storeManager.listProducts()) && totalPrice == storeManager.getTotalProductPrice()) {
            System.out.println("True");
        }
    }
}
