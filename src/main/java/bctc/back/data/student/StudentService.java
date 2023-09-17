package bctc.back.data.student;

import bctc.back.data.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService{
    List<Student> findAll();
}
