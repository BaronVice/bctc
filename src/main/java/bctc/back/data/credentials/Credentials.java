package bctc.back.data.credentials;

import bctc.back.data.users.Parent;
import bctc.back.data.users.student.Student;
import bctc.back.data.users.Tutor;
import bctc.back.security.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "credentials", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Student student;
    @OneToOne(mappedBy = "credentials", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Parent parent;
    @OneToOne(mappedBy = "credentials", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Tutor tutor;

}