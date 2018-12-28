package com.spring.reactive.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student_score")
@Data
public class StudentScore {
    @EmbeddedId
    private CompositeId id;
    private int score;

    @ManyToOne
    @MapsId("compositeId")
    @JoinColumns({
            @JoinColumn(name = "last_name", referencedColumnName = "last_name"),
            @JoinColumn(name = "first_name", referencedColumnName = "first_name")
    })
    private Student student;

    @Data
    @Embeddable
    @Accessors(chain = true)
    public static class CompositeId implements Serializable {
        @MapsId
        private Student.CompositeId compositeId;
        private String subject;
    }
}
