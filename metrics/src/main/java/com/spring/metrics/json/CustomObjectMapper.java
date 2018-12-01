package com.spring.metrics.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        registerModule(new Module());
        setSerializerFactory(getSerializerFactory().withSerializerModifier(new TagListSerializerModifier()));
    }
}
