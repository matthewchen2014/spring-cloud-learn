package com.spring.cloud.service.provider.controller;

import com.spring.cloud.service.provider.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class BookController {
    @GetMapping("/book/{id}")
    public Book findBook(@PathVariable Integer id) {
        System.out.println("invoke findBook method...");
        if (id == 1) {
            return new Book(1, "李自成", "姚雪垠", "人民文学出版社", LocalDate.of(2010, 05, 1));
        } else if (id == 2) {
            return new Book(2, "中国文学简史", "林庚", "清华大学出版社", LocalDate.of(1998, 6, 1));
        } else {
            return new Book(3, "文学改良刍议", "胡适", "无", LocalDate.of(1943, 5, 8));
        }
    }
}
