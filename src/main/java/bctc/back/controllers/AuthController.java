package bctc.back.controllers;

import bctc.back.data.model.Student;
import bctc.back.data.user.User;
import bctc.back.data.student.StudentRepository;
import bctc.back.data.user.IUserRepository;
import bctc.back.exception.UserAlreadyExistsException;
import bctc.back.security.AuthRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final IUserRepository IUserRepository;
    private final StudentRepository studentRepository;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/public-test")
    public ResponseEntity<?>publicTest(@Validated @RequestBody AuthRequestDto request) {
        return ResponseEntity.ok("That's all good man");
    }

    @PostMapping("/signup")
    public ResponseEntity<?>registerUser(@Validated @RequestBody AuthRequestDto request){
        Optional<User> foundUser = IUserRepository.findByUsername(request.username());
        if (foundUser.isPresent()) throw new UserAlreadyExistsException();

        String encodedPassword = passwordEncoder.encode(request.password());

        switch (request.role()){
            case STUDENT -> {
                Student newStudent = new Student();
                newStudent.setPassword(encodedPassword);
                newStudent.setUsername(request.username());
                studentRepository.save(newStudent);
                return ResponseEntity.ok(newStudent);
            }
            default -> {
                throw new RuntimeException("Unknown role provided");
            }
        }
//        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<?>authenticateUser( @RequestBody AuthRequestDto request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok("Welcome in, mf");
    }
}
