package bctc.back.data.credentials;

import bctc.back.data.users.parent.Parent;
import bctc.back.data.users.student.Student;
import bctc.back.data.users.turor.Tutor;
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
    private Long id;
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

    public void connectUser(Role role){
        switch (role){
            case STUDENT -> {
                Student s = new Student();
                s.setCredentials(this);
                this.setStudent(s);
            }
            case PARENT -> {
                Parent p = new Parent();
                p.setCredentials(this);
                this.setParent(p);
            }
            case TUTOR -> {
                Tutor t = new Tutor();
                t.setCredentials(this);
                this.setTutor(t);
            }
//            default -> throw new RuntimeException("Unknown role provided");
        }
    }

}