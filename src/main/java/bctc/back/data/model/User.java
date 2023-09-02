package bctc.back.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "_user")
public class User implements UserDetails, CredentialsContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String accountPassword;
    private boolean isActive = true;
    private boolean isNonLocked = true;
    private boolean isNonExpired = true;
    private boolean isAccountNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // todo figure out authorities handling
        return AuthorityUtils.createAuthorityList("Student");
    }

    @Override
    public String getPassword() {
        return accountPassword;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    @Override
    public void eraseCredentials() {
        this.accountPassword = null;
    }
}