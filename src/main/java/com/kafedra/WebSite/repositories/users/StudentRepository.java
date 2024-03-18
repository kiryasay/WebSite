package com.kafedra.WebSite.repositories.users;

import com.kafedra.WebSite.entities.users.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByGroup(String group);

    List<Student> findByCourse(String group);
}