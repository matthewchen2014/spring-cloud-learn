package com.spring.cloud.service.consumer.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.spring.cloud.service.consumer.command.BookConsumerCommand;
import com.spring.cloud.service.consumer.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class BookService {
    @Autowired
    private RestTemplate restTemplate;

    public Book findBook(Integer id) {
        HystrixRequestContext.initializeContext();
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("bookCommandKey");
        Book book1 = new BookConsumerCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(commandKey), restTemplate, id).execute();
        Book book2 = new BookConsumerCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(commandKey), restTemplate, id).execute();
        Book book3 = new BookConsumerCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(commandKey), restTemplate, id).execute();
        System.out.println("book1: " + book1);
        System.out.println("book2: " + book2);
        System.out.println("book3: " + book3);
        return new BookConsumerCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")).andCommandKey(commandKey), restTemplate, id).execute();
    }

    //@com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
    public Book findCachedBook(Integer id) {
        log.info("find book for id {}", id);
        return restTemplate.getForObject("http://service-provider/book/{id}", Book.class, id);
    }
}
