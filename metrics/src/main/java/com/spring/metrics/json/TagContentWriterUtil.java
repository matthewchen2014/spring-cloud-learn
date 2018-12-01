package com.spring.metrics.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.spring.metrics.model.AdvancedTag;
import com.spring.metrics.model.Tag;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class TagContentWriterUtil {
    public static void writeTagContent(JsonGenerator jsonGenerator, Tag tag) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", tag.getId());
        jsonGenerator.writeStringField("content", tag.getContent());
        jsonGenerator.writeEndObject();
    }

    public static void writeAdvancedTagContent(JsonGenerator jsonGenerator, AdvancedTag advancedTag) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", advancedTag.getId());
        jsonGenerator.writeStringField("content", advancedTag.getContent());
        jsonGenerator.writeObjectFieldStart("description");
        jsonGenerator.writeStringField("desc", advancedTag.getDescription().getDesc());
        jsonGenerator.writeStringField("createDate", advancedTag.getDescription().getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();
    }
}
