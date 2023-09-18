package bctc.back.data.user;

import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserDetailsService extends UserDetailsPasswordService, UserDetailsService {
    User delete(String id);
    User update(String id, User user);
    List<User> findAll();
    User findById(String id);
    User findByUsername(String username);
}