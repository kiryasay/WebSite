package com.kafedra.WebSite.services.users;

import com.kafedra.WebSite.dto.users.RegistrationTeacherDto;
import com.kafedra.WebSite.dto.users.RegistrationUserDto;
import com.kafedra.WebSite.dto.users.TeacherDto;
import com.kafedra.WebSite.entities.users.Teacher;
import com.kafedra.WebSite.entities.users.User;
import com.kafedra.WebSite.repositories.users.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserService userService;

    public Teacher createNewTeacher(RegistrationTeacherDto registrationTeacherDto){

        RegistrationUserDto registrationUserDto = new RegistrationUserDto();
        registrationUserDto.setPassword(registrationTeacherDto.getPassword());
        registrationUserDto.setUsername(registrationTeacherDto.getUsername());
        registrationUserDto.setRoles(registrationTeacherDto.getRoles());
        // Создание нового пользователя
        User newUser = userService.createNewUser(registrationUserDto);
        // Создание нового учителя
        Teacher teacher = new Teacher();
        teacher.setUser(newUser);
        teacher.setName(registrationTeacherDto.getName());
        teacher.setMail(registrationTeacherDto.getMail());
        teacher.setPhone(registrationTeacherDto.getPhone());
        teacher.setTelegram(registrationTeacherDto.getTelegram());
        teacher.setVkontakte(registrationTeacherDto.getVkontakte());
        teacher.setDescription(registrationTeacherDto.getDescription());
        // Сохранение учителя
        teacherRepository.save(teacher);

        return teacher;
    }

    public Teacher editTeacher(TeacherDto teacherDto, Long id){
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher != null) {
            teacher.setName(teacherDto.getName());
            teacher.setMail(teacherDto.getMail());
            teacher.setTelegram(teacherDto.getTelegram());
            teacher.setPhone(teacherDto.getPhone());
            teacher.setDescription(teacherDto.getDescription());
            teacher.setVkontakte(teacherDto.getVkontakte());

            // Сохранение обновленного учителя в репозитории
            teacher = teacherRepository.save(teacher);
        }
        return teacher;
    }

    public Optional<Teacher> teacherInfo(Long id)
    {

        Optional<Teacher> teacher;
        teacher = teacherRepository.findById(id);
        teacher.get().setUser(null);

        return teacher;
    }

    public Optional<Teacher> findById(Long id){
        return teacherRepository.findById(id);
    }
}
