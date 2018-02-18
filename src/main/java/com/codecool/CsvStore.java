package com.codecool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvStore extends Store {

    @Override
    public List<Product> getAllProduct(){
        return getListOfProducts();
    }

    @Override
    public void storeProduct(Product product) {
        getListOfProducts().add(product);
    }

    @Override
    public void store(Product product){
        storeProduct(product);
        saveToCsv(getAllProduct());
    }

    private void saveToCsv(List<Product> list) {
        if (list.size() != 0) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("store.csv"));
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof CDProduct) {
                        CDProduct cd = (CDProduct) list.get(i);
                        sb.append("name: " + cd.getName() + ",");
                        sb.append("price: " + cd.getPrice() + ",");
                        sb.append("tracks: " + cd.getSize() + "\n");
                    } else {
                        BookProduct book = (BookProduct) list.get(i);
                        sb.append("name: " + book.getName() + ",");
                        sb.append("price: " + book.getPrice() + ",");
                        sb.append("pages: " + book.getSize() + "\n");
                    }
                }

                bw.write(sb.toString());
                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
