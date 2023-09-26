package bctc.back.controllers;

import bctc.back.data.credentials.CredentialsDetails;
import bctc.back.data.users.student.Student;
import bctc.back.data.users.student.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentsController {
    private final IStudentService studentService;

    @GetMapping("/showUserInfo")
    public Student getStudent(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CredentialsDetails details = (CredentialsDetails) authentication.getDetails();

        return details.getCredentials().getStudent();
    }

    @GetMapping("/index")
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }
}