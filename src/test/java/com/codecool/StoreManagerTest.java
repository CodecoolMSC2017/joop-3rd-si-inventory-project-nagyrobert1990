package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreManagerTest {

    StoreManager storeManager;
    PersistentStore ps;
    String listOfEverything;
    int totalPrice;

    @BeforeEach
    void setUp() {
        StoreManager storeManager = new StoreManager();
        PersistentStore ps = new PersistentStore();
        storeManager.addStorage(ps);
        storeManager.addCDProduct("Metallica", 14, 12);
        storeManager.addBookProduct("Silence of the Lambs", 11, 100);
    }

    @Test
    void listProducts() {
        assertEquals("name: Metallica price: 14 size: 12 type: cd\nname: Silence of the Lambs price: 11 size: 100 type: book\n",storeManager.listProducts());
    }

    @Test
    void getTotalProductPrice() {
        assertEquals(26, storeManager.getTotalProductPrice());
    }
}