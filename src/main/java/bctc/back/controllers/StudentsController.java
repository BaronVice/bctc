package bctc.back.controllers;

import bctc.back.data.model.Student;
import bctc.back.data.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentsController {
    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }
}