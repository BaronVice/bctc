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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String accountPassword;
    private boolean isActive = true;
    private boolean isNonLocked = true;
    private boolean isNonExpired = true;
    private boolean isAccountNonExpired = true;
}