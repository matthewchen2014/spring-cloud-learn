package com.spring.metrics.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.spring.metrics.model.Tag;

import java.io.IOException;
import java.util.Objects;

public class TagJsonSerializer extends JsonSerializer<Tag> {
    @Override
    public void serialize(Tag tag, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (Objects.isNull(tag.getContent())) {
            jsonGenerator.writeNull();
        } else {
            TagContentWriterUtil.writeTagContent(jsonGenerator, tag);
        }
    }
}
