package bctc.back.data.credentials;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CredentialsService implements ICredentialsDetails {
    private final CredentialsRepository credentialsRepository;

    @Override
    public Credentials findById(long id) {
        return credentialsRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    @Override
    public Credentials findByUsername(String username) {
        return credentialsRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    @Override
    public List<Credentials> findAll() {
        return credentialsRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Credentials credentials){
        credentialsRepository.save(credentials);
    }

    @Override
    @Transactional
    public void update(long id, Credentials credentials) {
        credentials.setId(id);
        credentialsRepository.save(credentials);
    }

    @Override
    @Transactional
    public void delete(long id) {
        credentialsRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDetails updatePassword(UserDetails credentials, String newPassword) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials credentials = credentialsRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username: " + username)
        );

        return new CredentialsDetails(credentials);
    }
}
