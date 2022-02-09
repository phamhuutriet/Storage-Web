package com.example.storageapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    @Getter @Setter private String name;

    @Column(nullable = false)
    @Getter @Setter private float price;

    @Column
    @Getter @Setter private float rating;

    @Column(nullable = false)
    @Getter @Setter private Date createdAt;

    @Column(nullable = false)
    @Getter @Setter private String company;

    public Product() {
        this.createdAt = new Date();
    }

}
