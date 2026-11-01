package com.demo.entity;

import lombok.Data;

@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private String category;
    private Integer stock;
    private String status;
}
