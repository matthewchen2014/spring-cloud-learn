package com.spring.reactive.repository;

import com.spring.reactive.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Student.CompositeId> {
}
