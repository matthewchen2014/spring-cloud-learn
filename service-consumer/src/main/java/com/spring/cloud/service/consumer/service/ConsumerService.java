package com.spring.cloud.service.consumer.service;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.spring.cloud.service.consumer.command.ServiceConsumerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.Future;

@Service
public class ConsumerService {
    @Autowired
    private RestTemplate restTemplate;


    public String getConsumerInfo() {
        ServiceConsumerCommand command = new ServiceConsumerCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate);
        //synchronized command.execute();
        //asynchronized Future future = command.queue(); future.get();
        return command.execute();
    }

    @HystrixCommand(fallbackMethod = "fallback", ignoreExceptions = ArithmeticException.class)
    public String getConsumerInfoByAnnotation() {
        return restTemplate.getForObject("http://service-provider/hello", String.class);
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Future<String> getConsumerInfoAsync() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return getConsumerInfoByAnnotation();
            }
        };
    }

    public String fallback(Throwable throwable) {
        System.out.println(throwable);
        return "fallback";
    }
}
