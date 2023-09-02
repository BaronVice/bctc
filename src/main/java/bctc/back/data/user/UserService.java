package bctc.back.data.user;

import bctc.back.data.model.User;
import bctc.back.security.AuthRequestDto;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import java.util.List;

public interface UserService extends UserDetailsPasswordService {
    User registerUser(AuthRequestDto authRequestDto);
    User delete(String id);
    User update(String id, User user);
    List<User> findAll();
    User findById(String id);
    User findByUsername(String username);
}
