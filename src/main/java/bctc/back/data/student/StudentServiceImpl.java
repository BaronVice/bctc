package bctc.back.data.student;

import bctc.back.data.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}