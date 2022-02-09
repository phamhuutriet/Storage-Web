package com.example.storageapi.specifications;

import com.example.storageapi.models.product.Product;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class ProductSpecification {
    public static Specification<Product> withStringFilter(String feature, String value) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (value == null || value.equals("")) return cb.isTrue(cb.literal(true));
                return cb.like(root.get(feature), "%" + value + "%");
            }
        };
    }

    public static Specification<Product> withNumericFilter(String feature, String operation, double value) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (value == 0.0 && (operation == null || operation.equals(""))) return cb.isTrue(cb.literal(true));
                if (operation.equals(">")) return cb.greaterThan(root.get(feature), value);
                if (operation.equals(">=")) return cb.greaterThanOrEqualTo(root.get(feature), value);
                if (operation.equals("=")) return cb.equal(root.get(feature), value);
                if (operation.equals("<")) return cb.lessThan(root.get(feature), value);
                if (operation.equals("<=")) return cb.lessThanOrEqualTo(root.get(feature), value);
                return null;
            }
        };
    }


}
