package com.spring.metrics.controller;

import com.spring.metrics.model.AdvancedTag;
import com.spring.metrics.model.College;
import com.spring.metrics.model.Description;
import com.spring.metrics.model.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;

@RestController
@RequestMapping("/college")
public class CollegeController {
    @GetMapping
    public College getCollege() {
        AdvancedTag advancedTag = new AdvancedTag();
        advancedTag.setId("tag1");
        advancedTag.setContent("It's very smart");
        Tag tag = new Tag().setId("tag2");
        //tag.setContent("simple");
        advancedTag.setDescription(new Description().setDesc("This is a good desc").setCreateDate(LocalDate.of(2018, 1,1)));
        College college = new College().setName("PeiKing University").setAdvancedTag(advancedTag).setTag(tag);
        college.setAdvancedTags(Arrays.asList(advancedTag));
        college.setTags(Arrays.asList(new Tag().setId("tag3").setContent("Tag 3")));
        return college;
    }
}
