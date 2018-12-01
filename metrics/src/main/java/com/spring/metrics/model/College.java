package com.spring.metrics.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class College {
    private String name;
    private AdvancedTag advancedTag;
    private Tag tag;
    private List<AdvancedTag> advancedTags;
    private List<Tag> tags;
}
