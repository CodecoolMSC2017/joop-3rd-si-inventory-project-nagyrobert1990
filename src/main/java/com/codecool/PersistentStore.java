package com.codecool;

import java.util.List;

public class PersistentStore extends Store {

    @Override
    public List<Product> getAllProduct(){
        return getListOfProducts();
    }

    @Override
    public void storeProduct(Product product) {
        getListOfProducts().add(product);
    }
}
