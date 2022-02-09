package com.example.storageapi.models.product;


import java.util.Comparator;

public class ProductRatingComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getRating(), p2.getRating());
    }
}
