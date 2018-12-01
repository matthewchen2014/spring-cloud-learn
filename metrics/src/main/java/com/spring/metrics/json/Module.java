package com.spring.metrics.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.spring.metrics.model.AdvancedTag;
import com.spring.metrics.model.Tag;

public class Module extends SimpleModule {
    public Module() {
        addSerializer(Tag.class, new TagJsonSerializer());
        addSerializer(AdvancedTag.class, new AdvancedTagJsonSerializer());
    }
}
