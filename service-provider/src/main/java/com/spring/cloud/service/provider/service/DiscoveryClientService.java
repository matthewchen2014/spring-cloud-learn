package com.spring.cloud.service.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscoveryClientService {
    @Autowired
    private DiscoveryClient discoveryClient;

    public String getClients() {
        List<ServiceInstance> instanceList = discoveryClient.getInstances("service-provider");
        List<String> instanceStringList = instanceList.stream().map(instance -> instance.getHost() + ":" + instance.getPort()).collect(Collectors.toList());
        return "Service-Provider has the following instance: \n" + instanceStringList;
    }
}
