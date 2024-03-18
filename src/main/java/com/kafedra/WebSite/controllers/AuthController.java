package com.kafedra.WebSite.controllers;

import com.kafedra.WebSite.dto.jwt.JwtRequest;
import com.kafedra.WebSite.dto.users.*;
import com.kafedra.WebSite.services.AuthService;
import com.kafedra.WebSite.services.users.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TeacherService teacherService;
    @PostMapping("/auth")
    private ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/adduser")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);


    }

    @PostMapping("/addTeacher")
    public ResponseEntity<?> createNewTeacher(@RequestBody RegistrationTeacherDto registrationTeacherDto){
        return authService.createNewTeacher(registrationTeacherDto);
    }

    @GetMapping("/teacher/info/{id}")
    public ResponseEntity<?> TeacherInfo(@PathVariable Long id){
        return authService.TeacherInfo(id);
    }

    @PostMapping("/teacher/edit/{id}")
    public ResponseEntity<?> createNewTeacher(@RequestBody TeacherDto teacherDto, @PathVariable Long id){
        return authService.editTeacher(teacherDto, id);
    }


    @PostMapping("/addStudent")
    public ResponseEntity<?> createNewStudent(@RequestBody RegistrationStudentDto registrationStudentDto){
        return authService.createNewStudent(registrationStudentDto);
    }

    @GetMapping("/student/info/{id}")
    public ResponseEntity<?> StudentInfo(@PathVariable Long id){
        return authService.StudentInfo(id);
    }

    @PostMapping("/student/edit/{id}")
    public ResponseEntity<?> createNewStudent(@RequestBody StudentDto studentDto, @PathVariable Long id){
        return authService.editStudent(studentDto, id);
    }

    @GetMapping("/student/findbygroup/{group}")
    public ResponseEntity<?> findStudentByGroup(@PathVariable String group){
        return authService.FindStudentByGroup(group);
    }

    @GetMapping("/student/findbycourse/{course}")
    public ResponseEntity<?> findStudentByCourse(@PathVariable String course){
        return authService.FindStudentByCourse(course);
    }


}

