package com.spring.metrics.controller;

import com.spring.metrics.model.*;
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
        college.setTags(Arrays.asList(new Tag().setId("tag3")));

        Student student1 = new Student().setId(1).setName("Tom").setGender('M');
        Student student2 = new Student().setId(2).setName("Jack").setGender('M');
        college.setStudents(Arrays.asList(student1, student2));

        Student[] bestStudents = new Student[]{student1, student2};
        college.setBestStudents(bestStudents);
        return college;
    }
}
