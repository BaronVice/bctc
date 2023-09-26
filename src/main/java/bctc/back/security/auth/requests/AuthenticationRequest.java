package bctc.back.security.auth.requests;

import jakarta.validation.constraints.NotBlank;

// todo determine possible authentication inputs and handle them appropriately
public record AuthenticationRequest(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}