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

    @Override
    public Student save(Student student) {
        Optional<Student> foundStudent = studentRepository.findByUsername(student.getUsername());
        if (foundStudent.isPresent()) throw new RuntimeException("Holy shit, you're already exist, bro");

        student.setAccountPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }
}