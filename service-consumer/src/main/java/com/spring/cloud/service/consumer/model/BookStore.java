package com.spring.cloud.service.consumer.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookStore {
    private String name;
    private LocalDate createDate;
    private List<Book> books;
    private Book best;
    private Tag bookTag;
}
