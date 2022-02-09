package com.example.storageapi.services;
import com.example.storageapi.models.Product;
import com.example.storageapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.storageapi.specifications.ProductSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;


@Service
public class ProductService {
    private final static Set<String> numericTypes = new HashSet<>(Arrays.asList("rating", "price"));
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // METHODS
    public List<Product> fetchAll() { return productRepository.findAll(); }

    public Product createProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public List<Product> populateData(List<Product> populatedData) {
        return productRepository.saveAll(populatedData);
    }

    public List<Product> searchBySpec(String name, String company, String numericFilter) {
        Specification<Product> final_spec = where(withName(name)).and((withCompany(company)));

        if ( !(numericFilter == null || numericFilter.equals("")) ) {
            List<Specification<Product>> numericSpecs = handleNumericFilter(numericFilter);
            for (Specification<Product> spec : numericSpecs) {
                final_spec = final_spec.and(spec);
            }
        }

        return productRepository.findAll(final_spec);
    }

    private List<Specification<Product>> handleNumericFilter(String s) {
        List<Specification<Product>> numericFilterList = new ArrayList<>();

        String rx = "([=><]{1,2})+";
        StringBuilder stringBuilder = new StringBuilder();
        Pattern p = Pattern.compile(rx);
        Matcher m = p.matcher(s);

        while (m.find()) {
            String repString = m.group(1);
            if (repString != null) {
                m.appendReplacement(stringBuilder, "-" + repString + "-");
            }
        }
        m.appendTail(stringBuilder);

        String[] filters = stringBuilder.toString().split(",");
        for (String filter: filters) {
            String[] temp = filter.split("-");
            if (numericTypes.contains(temp[0])) {
                String type = temp[0], operation = temp[1], value = temp[2];
                System.out.printf("Test split each filter: type = %s, operation = %s, value = %s \n", type, operation, value);
                numericFilterList.add(withNumericFilter(type, operation, Float.parseFloat(value)));
            }
        }

        return numericFilterList;
    }
}
