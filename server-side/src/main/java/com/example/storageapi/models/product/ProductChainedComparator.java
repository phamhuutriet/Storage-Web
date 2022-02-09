package com.example.storageapi.models.product;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ProductChainedComparator implements Comparator<Product> {
    private List<Comparator<Product>> comparators;

    public ProductChainedComparator(List<Comparator<Product>> comparators) {
        this.comparators = comparators;
    }

    @Override
    public int compare(Product p1, Product p2) {
        for (Comparator<Product> comparator: comparators) {
            int result = comparator.compare(p1, p2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
