package bctc.back.controller;

import bctc.back.data.users.Parent;
import bctc.back.data.users.student.Student;
import bctc.back.data.credentials.Credentials;
import bctc.back.data.credentials.CredentialsDetails;
import bctc.back.data.credentials.CredentialsRepository;
import bctc.back.data.users.Tutor;
import bctc.back.exceptions.UserAlreadyExistsException;
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
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;
    final CredentialsRepository credentialsRepository;

    @PostMapping("/public-test")
    public ResponseEntity<?>publicTest(@Validated @RequestBody AuthRequestDto request) {
        return ResponseEntity.ok("That's all good man");
    }

    @PostMapping("/signup")
    public ResponseEntity<?>registerUser(@Validated @RequestBody AuthRequestDto request){
        checkIfExists(request.username());
        String encodedPassword = passwordEncoder.encode(request.password());

        Credentials credentials = Credentials.builder()
                .username(request.username())
                .password(encodedPassword)
                .role(request.role())
                .build();

        switch (request.role()){
            case STUDENT -> credentials.setStudent(new Student());
            case PARENT -> credentials.setParent(new Parent());
            case TUTOR -> credentials.setTutor(new Tutor());
//            default -> throw new RuntimeException("Unknown role provided");
        }

        credentialsRepository.save(credentials);

        return ResponseEntity.ok("User has been successfully created");
    }

    private void checkIfExists(String username){
        Optional<Credentials> foundUser = credentialsRepository.findByUsername(username);
        if (foundUser.isPresent())
            throw new UserAlreadyExistsException();
    }

    @PostMapping("/signin")
    public ResponseEntity<?>authenticateUser( @RequestBody AuthRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CredentialsDetails details = (CredentialsDetails) authentication.getPrincipal();

        return ResponseEntity.ok("Welcome in, mf");
    }
}
