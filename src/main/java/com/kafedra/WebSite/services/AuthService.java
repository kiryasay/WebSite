package com.kafedra.WebSite.services;

import com.kafedra.WebSite.dto.jwt.JwtRequest;
import com.kafedra.WebSite.dto.jwt.JwtResponse;
import com.kafedra.WebSite.dto.users.*;
import com.kafedra.WebSite.entities.users.Student;
import com.kafedra.WebSite.entities.users.Teacher;
import com.kafedra.WebSite.entities.users.User;
import com.kafedra.WebSite.exceptions.AppError;
import com.kafedra.WebSite.services.users.StudentService;
import com.kafedra.WebSite.services.users.TeacherService;
import com.kafedra.WebSite.services.users.UserService;
import com.kafedra.WebSite.utils.jwt.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;

    private final TeacherService teacherService;

    private final StudentService studentService;

    private final JwtTokenUtils jwtTokenUtils;

    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        System.out.println(userDetails);
        String token = jwtTokenUtils.generateToken(userDetails);
        System.out.println("token " + token);
        System.out.println("user name = " + jwtTokenUtils.getUsername(token));
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if(userService.findByUserName(registrationUserDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Такой пользователь уже существует"), HttpStatus.BAD_REQUEST);
        }
        User user = userService.createNewUser(registrationUserDto);

        return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getRoles()));
    }

    public ResponseEntity<?> createNewTeacher(@RequestBody RegistrationTeacherDto registrationTeacherDto) {
        if(userService.findByUserName(registrationTeacherDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Такой пользователь уже существует"), HttpStatus.BAD_REQUEST);
        }
        Teacher teacher = teacherService.createNewTeacher(registrationTeacherDto);

        return ResponseEntity.ok(200);
    }

    public  ResponseEntity<?> TeacherInfo(Long id)
    {
        return  ResponseEntity.ok(teacherService.teacherInfo(id));
    }

    public ResponseEntity<?> editTeacher(@RequestBody TeacherDto teacherDto, Long id) {
        if(teacherService.findById(id).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Такого преподавателя не существует"), HttpStatus.BAD_REQUEST);
        }
        teacherService.editTeacher(teacherDto, id);

        return ResponseEntity.ok(200);
    }


    public ResponseEntity<?> createNewStudent(@RequestBody RegistrationStudentDto registrationStudentDto) {
        if(userService.findByUserName(registrationStudentDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Такой пользователь уже существует"), HttpStatus.BAD_REQUEST);
        }
        Student student = studentService.createNewStudent(registrationStudentDto);

        return ResponseEntity.ok(200);
    }

    public  ResponseEntity<?> StudentInfo(Long id)
    {
        return  ResponseEntity.ok(studentService.studentInfo(id));
    }

    public ResponseEntity<?> editStudent(@RequestBody StudentDto studentDto, Long id) {
        if(studentService.findById(id).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Такого студента не существует"), HttpStatus.BAD_REQUEST);
        }
        studentService.editStudent(studentDto, id);

        return ResponseEntity.ok(200);
    }

    public  ResponseEntity<?> FindStudentByGroup(String group)
    {
        List<Student> studentList = studentService.findByGroup(group);
        studentList.forEach(student -> student.setUser(null));
        return  ResponseEntity.ok(studentList);
    }

    public  ResponseEntity<?> FindStudentByCourse(String course)
    {
        List<Student> studentList = studentService.findByCourse(course);
        studentList.forEach(student -> student.setUser(null));
        return  ResponseEntity.ok(studentList);
    }


}
