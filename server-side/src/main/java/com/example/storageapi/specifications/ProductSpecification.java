package com.example.storageapi.specifications;

import com.example.storageapi.models.Product;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecification {
    public static Specification<Product> withName(String name) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                System.out.println(name == null);
                if (name == null || name.equals("")) return cb.isTrue(cb.literal(true));
                return cb.equal(root.get("name"), name);
            }
        };
    }
}
