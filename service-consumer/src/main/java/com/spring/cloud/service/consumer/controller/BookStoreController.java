package com.spring.cloud.service.consumer.controller;

import com.spring.cloud.service.consumer.model.Book;
import com.spring.cloud.service.consumer.model.BookStore;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;

@RestController
@RequestMapping("/books")
public class BookStoreController {
    @GetMapping("/{name}")
    public BookStore find(@PathVariable String name) {
        BookStore bookStore = new BookStore();
        bookStore.setName(name);
        Book book1 = new Book();
        book1.setAuthor("tom1");
        book1.setTitle("Best idea");
        book1.setId(1);
        book1.setPublishDate(LocalDate.of(2018, 1, 1));
        book1.setPublisher("Shanghai");

        Book book2 = new Book();
        book2.setAuthor("tom2");
        book2.setTitle("History of China");
        book2.setPublishDate(LocalDate.of(2018,2,1));
        bookStore.setBest(book1);
        bookStore.setBooks(Arrays.asList(book1, book2));
        return bookStore;
    }
}
