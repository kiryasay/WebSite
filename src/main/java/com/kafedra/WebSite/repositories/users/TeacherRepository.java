package com.kafedra.WebSite.repositories.users;

import com.kafedra.WebSite.entities.users.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
}