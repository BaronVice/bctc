package bctc.back.controller;

import bctc.back.data.model.User;
import bctc.back.data.student.StudentRepository;
import bctc.back.data.user.UserRepository;
import bctc.back.security.AuthRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    final PasswordEncoder passwordEncoder;
    final UserRepository userRepository;
    final StudentRepository studentRepository;

    @PostMapping("/signup")
    public ResponseEntity<?>registerUser(@Validated @RequestBody AuthRequestDto request){
        return ResponseEntity.ok("That's all good man");

//        Optional<User> foundUser = userRepository.findByUsername(authRequestDto.username());
//        if (foundUser.isEmpty()) throw new RuntimeException("Holy shit, you're already exist, bro");
//
//        String encodedPassword = passwordEncoder.encode(authRequestDto.password());
//        User newUser = User.builder()
//                .accountPassword(encodedPassword)
//                .username(authRequestDto.username())
//                .build();
//
//        userRepository.save(newUser);
//
//        return newUser;
    }
}
