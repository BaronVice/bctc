package bctc.back.data.user;

import bctc.back.data.model.User;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import java.util.List;

public interface UserDetailsService extends UserDetailsPasswordService, org.springframework.security.core.userdetails.UserDetailsService {
    User delete(String id);
    User update(String id, User user);
    List<User> findAll();
    User findById(String id);
    User findByUsername(String username);
}