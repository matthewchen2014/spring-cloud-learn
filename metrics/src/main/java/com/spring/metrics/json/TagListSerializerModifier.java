package com.spring.metrics.json;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.util.List;
import java.util.Set;

public class TagListSerializerModifier extends BeanSerializerModifier {
    private JsonSerializer collectionFilterSerializer = new TagCollectionJsonSerializer();

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        for (int i = 0; i < beanProperties.size(); i++) {
            BeanPropertyWriter writer = beanProperties.get(i);
            if (isTagList(writer)) {
                writer.assignSerializer(collectionFilterSerializer);
            }
        }
        return super.changeProperties(config, beanDesc, beanProperties);
    }

    private boolean isTagList(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.isArray() || writer.getType().isTypeOrSubTypeOf(Set.class) || writer.getType().isTypeOrSubTypeOf(List.class);
    }
}
