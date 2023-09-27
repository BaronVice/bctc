package bctc.back.security.auth;

import bctc.back.data.credentials.Credentials;
import bctc.back.data.credentials.CredentialsRepository;
import bctc.back.exceptions.UserAlreadyExistsException;
import bctc.back.security.auth.requests.AuthenticationRequest;
import bctc.back.security.auth.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;
    final CredentialsRepository credentialsRepository;

    public String register(RegisterRequest request){
        checkIfExists(request.username());
        String encodedPassword = passwordEncoder.encode(request.password());

        Credentials credentials = Credentials.builder()
                .username(request.username())
                .password(encodedPassword)
                .role(request.role())
                .build();

        credentials.connectUser(request.role());
        credentialsRepository.save(credentials);

        return "Registered;\nTODO: figure out AuthenticationResponse";
    }

    private void checkIfExists(String username){
        Optional<Credentials> foundUser = credentialsRepository.findByUsername(username);
        if (foundUser.isPresent())
            throw new UserAlreadyExistsException();
    }

    public Authentication authenticate(AuthenticationRequest request){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

//        String username = request.username();
//        String password = request.password();
//        return new UsernamePasswordAuthenticationToken(
//                credentialsRepository.findByUsername(username).get(),
//                password,
//                Collections.emptyList()
//        );

        return authentication;
    }
}
