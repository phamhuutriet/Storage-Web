package store.store.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.store.models.Product;
import store.store.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // METHODS
    @GetMapping("")
    public ResponseEntity fetchAll() {
        System.out.println("Fetch data successfully");
        return ResponseEntity.ok(productService.fetchAll());
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
        return ResponseEntity.ok(productService.createProduct(newProduct));
    }

}
