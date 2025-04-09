package ru.vcarstein.books.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vendor_code")
    private String vendorCode;

    private String title;
    private String year;
    private String brand;
    private int stock;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
}