package com.spring.metrics.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        registerModule(new Module());
        setSerializerFactory(getSerializerFactory().withSerializerModifier(new TagListSerializerModifier()));
    }
}
