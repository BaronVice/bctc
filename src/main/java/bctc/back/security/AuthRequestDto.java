package bctc.back.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// todo determine possible authentication inputs and handle them appropriately
public record AuthRequestDto(
        @NotBlank
        String username,
        @NotBlank
        String password) {
}