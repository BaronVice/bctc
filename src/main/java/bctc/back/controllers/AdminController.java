package bctc.back.controllers;

import bctc.back.data.credentials.Credentials;
import bctc.back.data.credentials.CredentialsRepository;
import bctc.back.data.credentials.Role;
import bctc.back.data.users.parent.ParentRepository;
import bctc.back.data.users.student.StudentRepository;
import bctc.back.data.users.turor.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final CredentialsRepository credentialsRepository;
    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;
    private final BCryptPasswordEncoder encoder;

    @GetMapping("/getUserInfo")
    public ResponseEntity<?> getUserInfo(@RequestParam String username){
        Credentials credentials = credentialsRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format("User with username=%s is not found", username)
                )
        );

        return ResponseEntity.ok(
                switch (credentials.getRole()){
                    case PARENT -> parentRepository.findById(credentials.getId()).get();
                    case STUDENT -> studentRepository.findById(credentials.getId()).get();
                    case TUTOR -> tutorRepository.findById(credentials.getId()).get();
                    default -> throw new UsernameNotFoundException("This user is not accessible");
                }
                );
    }

    @PostMapping("/giveManager")
    public ResponseEntity<?> createManager(){
        // TODO: create script to generate credentials
        // TODO: encrypt password
        credentialsRepository.save(
                Credentials.builder()
                        .username("someUsername")
                        .password("somePassword")
                        .role(Role.MANAGER)
                        .build()
        );

        return ResponseEntity.ok("Manager is successfully created");
    }

    @PatchMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(
            @RequestParam String username,
            @RequestParam String password
    ){
        Credentials credentials = credentialsRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format("User with username=%s is not found", username)
                )
        );

        credentials.setPassword(encoder.encode(password));
        credentialsRepository.save(credentials);

        return ResponseEntity.ok("Password was changed");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam String username){
        Credentials credentials = credentialsRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format("User with username=%s is not found", username)
                )
        );

        credentialsRepository.delete(credentials);

        return ResponseEntity.ok("Deleted");
    }
}
