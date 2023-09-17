package bctc.back.controller;

import bctc.back.security.AuthRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/signup")
    public ResponseEntity<?>registerUser(@Valid AuthRequestDto request){
        return ResponseEntity.ok("That's all good man");
    }
}
