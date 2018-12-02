package com.spring.metrics.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Student {
    private int id;
    private String name;
    private char gender;
}
