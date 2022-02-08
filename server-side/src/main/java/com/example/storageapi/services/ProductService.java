package com.example.storageapi.services;
import com.example.storageapi.models.Product;
import com.example.storageapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.storageapi.specifications.ProductSpecification.withName;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // METHODS
    public List<Product> fetchAll() {
        return productRepository.findAll();
    }

    public Product createProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public List<Product> populateData(List<Product> populatedData) {
        return productRepository.saveAll(populatedData);
    }

    public List<Product> searchBySpec(String name) {
        System.out.println("The name is " + name);
        return productRepository.findAll(withName(name));
    }

}
