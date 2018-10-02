package com.spring.cloud.service.consumer.command;

import com.netflix.hystrix.HystrixCommand;
import com.spring.cloud.service.consumer.model.Book;
import org.springframework.web.client.RestTemplate;

public class BookConsumerCommand extends HystrixCommand<Book> {
    private RestTemplate restTemplate;
    private Integer id;

    public BookConsumerCommand(Setter setter, RestTemplate restTemplate, Integer id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected Book run() {
        System.out.println("send request to service-provider: http://service-provider/book/" + id);
        return restTemplate.getForObject("http://service-provider/book/{id}", Book.class, id);
    }

    @Override
    protected String getCacheKey() {
        return id.toString();
    }
}
