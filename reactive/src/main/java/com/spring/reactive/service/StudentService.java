package com.spring.reactive.service;

import com.spring.reactive.model.Student;
import com.spring.reactive.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void save(Student student) {
        studentRepository.save(student);
    }

    public Student find(String fistName, String lastName) {
        Student.CompositeId id = new Student.CompositeId();
        id.setFirstName(fistName).setLastName(lastName);
        Optional<Student> student = studentRepository.findById(id);
        student.get().getStudentScores().forEach(studentScore -> log.info("{}-{}", studentScore.getId().getSubject(), studentScore.getScore()));
        return student.orElseThrow(NoSuchElementException::new);
    }
}
