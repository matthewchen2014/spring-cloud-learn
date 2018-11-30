package com.spring.cloud.service.consumer.json;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.util.List;
import java.util.Set;

public class MyBeanSerializerModifier extends BeanSerializerModifier {
    private ListJsonSerializer listJsonSerializer = new ListJsonSerializer();

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        for (int i = 0; i < beanProperties.size() ; i++) {
            BeanPropertyWriter beanPropertyWriter = beanProperties.get(i);
            if (isList(beanPropertyWriter)) {
                beanPropertyWriter.assignSerializer(listJsonSerializer);
            }
        }
        return beanProperties;
    }

    private boolean isList(BeanPropertyWriter beanPropertyWriter) {
        Class<?> clazz = beanPropertyWriter.getType().getRawClass();
        return clazz.isArray() || clazz.equals(List.class) || clazz.equals(Set.class);
    }
}
