package com.spring.cloud.service.consumer.controller;

import com.spring.cloud.service.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceConsumerController {
    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/ribbon-consumer")
    public String consume() {
        return consumerService.getConsumerInfoByAnnotation();
    }
}
