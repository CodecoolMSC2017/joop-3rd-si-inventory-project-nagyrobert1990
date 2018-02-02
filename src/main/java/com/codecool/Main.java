package com.codecool;

public class Main {

    public static void main(String[] args) {

        Product asd = new CDProduct("Iron Maiden", 12, 10);
        StoreManager storeManager = new StoreManager();
        PersistentStore ps = new PersistentStore();
        storeManager.addStorage(ps);
        ps.storeProduct(asd);
        storeManager.addCDProduct("Metallica", 15, 12);
        storeManager.addBookProduct("Silence of the Lambs", 11, 100);
        String listOfEverything = storeManager.listProducts();
        int totalPrice = storeManager.getTotalProductPrice();

        System.out.println(listOfEverything);
        System.out.println(Integer.toString(totalPrice));
    }
}
