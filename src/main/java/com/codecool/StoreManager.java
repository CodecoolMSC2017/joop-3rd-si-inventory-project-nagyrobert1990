package com.codecool;

public class StoreManager {

    StorageCapable storage;

    public void addStorage(StorageCapable storage){
        this.storage = storage;
    }

    public void addCDProduct(String name, int price, int tracks){
        storage.storeCDProduct(name, price,tracks);
    }

    public void addBookProduct(String name, int price, int pages){
        storage.storeBookProduct(name, price, pages);
    }

    public String listProducts(){
        String lst = "";
        for (Product iterateProducts : storage.getAllProduct()){
            String tp = "cd";
            String a = iterateProducts.getName();
            String b = Integer.toString(iterateProducts.getPrice());
            String c = Integer.toString(iterateProducts.getSize());
            if (iterateProducts instanceof BookProduct){
                tp = "book";
            }
            lst += "name: " + a + "price: " + b + "size: " + c + "type: " + tp + "\n";
        }
        return lst;
    }

    public int getTotalProductPrice(){
        int sum = 0;
        for (Product iterateProducts : storage.getAllProduct()){
            sum += iterateProducts.getPrice();
        }
        return sum;
    }
}
