package com.spring.metrics.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.spring.metrics.model.AdvancedTag;
import java.io.IOException;
import java.util.Objects;

public class AdvancedTagJsonSerializer extends JsonSerializer<AdvancedTag> {
    @Override
    public void serialize(AdvancedTag advancedTag, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (Objects.isNull(advancedTag.getContent())) {
            jsonGenerator.writeNull();
        } else {
            TagContentWriterUtil.writeAdvancedTagContent(jsonGenerator, advancedTag);
        }
    }
}
