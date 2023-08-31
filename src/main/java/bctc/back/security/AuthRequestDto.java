package bctc.back.security;

// todo determine possible authentication inputs and handle them appropriately
public record AuthRequestDto(
        String username,
        String password) {
}
