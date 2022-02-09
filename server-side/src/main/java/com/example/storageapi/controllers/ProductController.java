package com.example.storageapi.controllers;
import com.example.storageapi.models.product.Product;
import com.example.storageapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // METHODS
    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
        return ResponseEntity.ok(productService.createProduct(newProduct));
    }

    @PostMapping("/postAll")
    public ResponseEntity<List<Product>> populateData(@RequestBody List<Product> populatedData) {
        return ResponseEntity.ok(productService.populateData(populatedData));
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> searchBySpec(@Nullable @RequestParam(name = "name") String name,
                                                      @Nullable @RequestParam(name = "company") String company,
                                                      @Nullable @RequestParam(name = "numericFilter") String numericFilter,
                                                      @Nullable @RequestParam(name = "sort") String sortQuery,
                                                      @Nullable @RequestParam(name = "page") String page,
                                                      @Nullable @RequestParam(name = "limit") String limit) {
        return ResponseEntity.ok(productService.searchBySpec(name, company, numericFilter, sortQuery, page, limit));
    }
}
