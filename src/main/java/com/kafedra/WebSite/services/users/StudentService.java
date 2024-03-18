package com.kafedra.WebSite.services.users;

import com.kafedra.WebSite.dto.users.RegistrationStudentDto;
import com.kafedra.WebSite.dto.users.RegistrationUserDto;
import com.kafedra.WebSite.dto.users.StudentDto;
import com.kafedra.WebSite.entities.users.Student;
import com.kafedra.WebSite.entities.users.User;
import com.kafedra.WebSite.repositories.users.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final UserService userService;

    public Student createNewStudent(RegistrationStudentDto registrationStudentDto){

        RegistrationUserDto registrationUserDto = new RegistrationUserDto();
        registrationUserDto.setPassword(registrationStudentDto.getPassword());
        registrationUserDto.setUsername(registrationStudentDto.getUsername());
        registrationUserDto.setRoles(registrationStudentDto.getRoles());
        // Создание нового пользователя
        User newUser = userService.createNewUser(registrationUserDto);
        // Создание нового учителя
        Student student = new Student();
        student.setUser(newUser);
        student.setName(registrationStudentDto.getName());
        student.setGroup(registrationStudentDto.getGroup());
        student.setCourse(registrationStudentDto.getCourse());
        // Сохранение учителя
        studentRepository.save(student);

        return student;
    }

    public Student editStudent(StudentDto studentDto, Long id){
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setName(studentDto.getName());
            student.setGroup(studentDto.getGroup());
            student.setCourse(studentDto.getCourse());

            // Сохранение обновленного учителя в репозитории
            student = studentRepository.save(student);
        }
        return student;
    }

    public Optional<Student> studentInfo(Long id)
    {

        Optional<Student> student;
        student = studentRepository.findById(id);
        student.get().setUser(null);

        return student;
    }

    public Optional<Student> findById(Long id){
        return studentRepository.findById(id);
    }

    public List<Student> findByGroup(String group){
        return studentRepository.findByGroup(group);
    }

    public List<Student> findByCourse(String course){
        return studentRepository.findByCourse(course);
    }

}
