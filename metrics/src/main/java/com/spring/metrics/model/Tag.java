package com.spring.metrics.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Tag {
    private String id;
    private String content;
}
