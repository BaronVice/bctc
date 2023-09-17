package bctc.back.data.user;

import bctc.back.data.model.School;
import bctc.back.security.Role;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
// Combines info from User entity and its extension (like student, tutor etc.)
class UserDetailsImpl implements UserDetails, CredentialsContainer {
    private Long id;
    private String username;
    private String accountPassword;
    private String email;
    private String phone;
    private boolean isActive = true;
    private boolean isNonLocked = true;
    private boolean isNonExpired = true;
    private boolean isAccountNonExpired = true;
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(role.toString());
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
