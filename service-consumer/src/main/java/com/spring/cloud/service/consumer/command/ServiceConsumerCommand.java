package com.spring.cloud.service.consumer.command;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

public class ServiceConsumerCommand extends HystrixCommand<String> {
    private RestTemplate restTemplate;

    public ServiceConsumerCommand(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() {
        return restTemplate.getForObject("http://service-provider/hello", String.class);
    }

    @Override
    protected String getFallback() {
        Throwable throwable = getExecutionException();
        System.out.println(throwable);
        return "fallback";
    }

    @Override
    protected String getCacheKey() {
        return super.getCacheKey();
    }
}
