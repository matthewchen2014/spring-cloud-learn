package com.spring.metrics.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdvancedTag extends Tag{
    private Description description;
}
