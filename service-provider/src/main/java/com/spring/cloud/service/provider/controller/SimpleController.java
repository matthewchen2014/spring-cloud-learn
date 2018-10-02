package com.spring.cloud.service.provider.controller;

import com.spring.cloud.service.provider.service.DiscoveryClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @Autowired
    private DiscoveryClientService discoveryClientService;
    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index() {
        return discoveryClientService.getClients();
    }

    @GetMapping("/hello")
    public String hello() {
        return "process request from client, endpoint port is " + port;
    }
}
