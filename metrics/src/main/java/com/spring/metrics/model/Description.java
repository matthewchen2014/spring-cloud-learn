package com.spring.metrics.model;

import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class Description {
    private String desc;
    private LocalDate createDate;
}
