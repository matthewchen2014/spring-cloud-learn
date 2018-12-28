package com.spring.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "student")
public class Student {
    @EmbeddedId
    private CompositeId id;
    private String email;
    private int age;
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentScore> studentScores;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    @Embeddable
    public static class CompositeId implements Serializable {
        @Column(name = "last_name")
        private String lastName;

        @Column(name = "first_name")
        private String firstName;
    }
}
