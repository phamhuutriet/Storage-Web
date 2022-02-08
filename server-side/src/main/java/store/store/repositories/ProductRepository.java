package store.store.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import store.store.models.Product;

import java.util.List;
import java.util.PrimitiveIterator;

public interface ProductRepository extends MongoRepository<Product, Integer> {
    // fetch all products -> findAll
    List<Product> findAll();
}
