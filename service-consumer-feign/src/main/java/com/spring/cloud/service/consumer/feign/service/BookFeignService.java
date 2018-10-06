package com.spring.cloud.service.consumer.feign.service;

import com.spring.cloud.service.consumer.feign.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-provider")
public interface BookFeignService {
    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    Book getBook(@PathVariable("id") Integer id);
}
