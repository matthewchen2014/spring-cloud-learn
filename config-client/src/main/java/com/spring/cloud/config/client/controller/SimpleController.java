package com.spring.cloud.config.client.controller;

import com.spring.cloud.config.client.model.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @Autowired
    private DB db;

    @GetMapping("/db")
    public DB getDB() {
        return db;
    }
}
