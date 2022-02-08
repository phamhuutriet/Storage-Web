package store.store.service;

import org.springframework.stereotype.Service;
import store.store.controllers.ProductController;
import store.store.models.Product;
import store.store.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private SequenceGeneratorService sequenceGeneratorService;

    public ProductService(ProductRepository productRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.productRepository = productRepository;
    }

    // METHODS
    public List<Product> fetchAll() {
        return productRepository.findAll();
    }

    public Product createProduct(Product newProduct) {
        newProduct.setId(sequenceGeneratorService.generateSequence(Product.SEQUENCE_NAME));
        return productRepository.save(newProduct);
    }

}
