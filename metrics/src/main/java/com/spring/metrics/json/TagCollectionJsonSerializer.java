package com.spring.metrics.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.spring.metrics.model.Tag;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCollectionJsonSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Class<?> t = o.getClass();
        if (t.isArray()) {
            Object[] array = (Object[])o;
            Supplier<Stream> supplier = () -> Arrays.stream(array);
            filter(jsonGenerator, supplier, o);
        } else if (o instanceof Set || o instanceof List) {
            Collection<Object> collection = (Collection)o;
            Supplier<Stream> supplier = () -> collection.stream();
                    filter(jsonGenerator, supplier, o);
        } else {
            jsonGenerator.writeObject(o);
        }
    }

    private void filter(JsonGenerator jsonGenerator, Supplier<Stream> supplier, Object object) throws IOException {
        if (hasEmptyTag(supplier.get())) {
            Object realWriteObject = supplier.get().filter(o -> {
                if (o instanceof Tag) {
                    return Objects.nonNull(((Tag) o).getContent());
                } else {
                    return true;
                }
            }).collect(Collectors.toList());
            jsonGenerator.writeObject(realWriteObject);
        } else {
            jsonGenerator.writeObject(object);
        }
    }

    private boolean hasEmptyTag(Stream stream) {
        return stream.anyMatch(o -> o instanceof Tag && Objects.isNull(((Tag) o).getContent()));
    }
}
