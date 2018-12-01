package com.spring.metrics.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.spring.metrics.model.Tag;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCollectionJsonSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Class<?> t = o.getClass();
        if (t.isArray()) {
            Object[] array = (Object[])o;
            filter(jsonGenerator, Arrays.stream(array));
        } else if (o instanceof Set || o instanceof List) {
            Collection<Object> collection = (Collection)o;
            collection.stream();
            filter(jsonGenerator, collection.stream());
        } else {
            jsonGenerator.writeObject(o);
        }
    }

    private void filter(JsonGenerator jsonGenerator, Stream stream) throws IOException {
        Object ojb = stream.filter(o -> {
            if (o instanceof Tag) {
                return Objects.nonNull(((Tag) o).getContent());
            } else {
                return true;
            }
        }).collect(Collectors.toList());
        jsonGenerator.writeObject(ojb);
    }
}
