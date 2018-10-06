package com.spring.cloud.service.consumer.feign.controller;

import com.spring.cloud.service.consumer.feign.model.Book;
import com.spring.cloud.service.consumer.feign.service.BookFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookFeignController {
    @Autowired
    private BookFeignService bookFeignService;

    @GetMapping("/book/{id}")
    public Book find(@PathVariable Integer id) {
        return bookFeignService.getBook(id);
    }
}
