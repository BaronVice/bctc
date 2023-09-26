package bctc.back.security.auth.requests;

import bctc.back.data.credentials.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotNull // or @NotBlank?
        Role role
) {
}
