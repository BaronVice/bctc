package bctc.back.data.user;

import bctc.back.data.model.User;
import bctc.back.security.AuthRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(AuthRequestDto authRequestDto) {
        Optional<User> foundUser = userRepository.findByUsername(authRequestDto.username());
        if (foundUser.isEmpty()) throw new RuntimeException("Holy shit, you're already exist, bro");

        String encodedPassword = passwordEncoder.encode(authRequestDto.password());
        User newUser = User.builder()
                .accountPassword(encodedPassword)
                .username(authRequestDto.username())
                .build();

        userRepository.save(newUser);

        return newUser;
    }

    @Override
    public User delete(String id) {
        return null;
    }

    @Override
    public User update(String id, User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }
}
