package com.spring.cloud.zuul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocalController {
    @GetMapping("/local")
    public String getLocal() {
        return "this is local api gateway";
    }
}
