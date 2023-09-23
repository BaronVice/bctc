package bctc.back.controllers;

import bctc.back.data.users.student.Student;
import bctc.back.data.users.student.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentsController {
    private final IStudentService studentService;

    @GetMapping("/index")
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }
}