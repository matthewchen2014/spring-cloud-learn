package com.spring.reactive;

import com.spring.reactive.model.Student;
import com.spring.reactive.model.StudentScore;
import com.spring.reactive.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void testSave() {
        Student student = new Student();
        student.setId(new Student.CompositeId("tom", "cat"));
        student.setAge(20);
        student.setEmail("tom@163.com");
        studentService.save(student);
    }

    @Test
    public void testCascade() {
        Student student = new Student();
        student.setId(new Student.CompositeId("tom", "cat"));
        student.setAge(20);
        student.setEmail("tom@163.com");
        StudentScore studentScore1 = new StudentScore();
        studentScore1.setId(new StudentScore.CompositeId().setSubject("English"));
        studentScore1.setScore(98);
        studentScore1.setStudent(student);

        StudentScore studentScore2 = new StudentScore();
        studentScore2.setId(new StudentScore.CompositeId().setSubject("Math"));
        studentScore2.setScore(95);
        studentScore2.setStudent(student);

        student.setStudentScores(Arrays.asList(studentScore1, studentScore2));
        studentService.save(student);
    }

    @Test
    public void testFind() {
        Student student = studentService.find("tom", "cat");
        assertEquals(student.getStudentScores().size(), 2);
    }
}
