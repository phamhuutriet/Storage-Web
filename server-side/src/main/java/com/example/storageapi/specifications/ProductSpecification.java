package com.example.storageapi.specifications;

import com.example.storageapi.models.Product;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class ProductSpecification {
    private final static List<String> companies = Arrays.asList("ikea", "liddy", "caressa", "marcos");
    private final static Set<String> companiesSet = new HashSet<>(companies);

    public static Specification<Product> withName(String name) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (name == null || name.equals("")) return cb.isTrue(cb.literal(true));
                return cb.like(root.get("name"), "%" + name + "%");
            }
        };
    }

    public static Specification<Product> withCompany(String company) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (company == null || company.equals("")) return cb.isTrue(cb.literal(true));
                return cb.equal(root.get("company"), company);
            }
        };
    }

    public static Specification<Product> withNumericFilter(String feature, String operation, float value) {
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
