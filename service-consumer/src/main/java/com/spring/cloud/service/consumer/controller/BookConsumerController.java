package com.spring.cloud.service.consumer.controller;

import com.spring.cloud.service.consumer.model.Book;
import com.spring.cloud.service.consumer.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookConsumerController {
    @Autowired
    private BookService bookService;

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable Integer id) {
        return bookService.findCachedBook(id);
    }
}
