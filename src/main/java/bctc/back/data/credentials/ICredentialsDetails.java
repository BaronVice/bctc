package bctc.back.data.credentials;

import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ICredentialsDetails extends UserDetailsPasswordService, UserDetailsService {
    Credentials findById(long id);
    Credentials findByUsername(String username);
    List<Credentials> findAll();
    void save(Credentials credentials);
    void update(long id, Credentials credentials);
    void delete(long id);
}