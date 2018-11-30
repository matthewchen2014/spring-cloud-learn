package com.spring.cloud.service.consumer.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.spring.cloud.service.consumer.model.Book;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ListJsonSerializer extends JsonSerializer {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(value instanceof List) {
            List list = (List)value;
            List<Object> results =
                    (List<Object>) list.stream().filter(obj -> {
                        Book book = (Book) obj;
                        return book.getPublisher() != null;
                    }).collect(Collectors.toList());
            gen.writeObject(results);
        } else {
            gen.writeObject(value);
        }
    }
}
