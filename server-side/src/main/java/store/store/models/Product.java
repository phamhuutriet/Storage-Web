package store.store.models;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("products")
public class Product {

    @Transient
    public static final String SEQUENCE_NAME = "products_sequence";

    @Id
    @Getter @Setter private long id;

    @Getter @Setter private String name;
    @Getter @Setter private Number price;
    @Getter @Setter private boolean featured;
    @Getter @Setter private Date createdAt;
    @Getter @Setter private String company;

    // Constructor
    public Product() {
        this.featured = false;
        this.createdAt = new Date();
    }
}
