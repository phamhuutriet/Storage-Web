package com.example.storageapi.models.product;


import java.util.Comparator;

public class ProductCompanyComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        return p1.getCompany().compareTo(p2.getCompany());
    }
}
