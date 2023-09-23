package bctc.back.data.users;

import bctc.back.data.credentials.Credentials;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class User {
    @Id
    @Column(name = "credentials_id")
    protected Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "credentials_id")
    protected Credentials credentials;

    protected String firstName;

    protected String lastName;

    protected int age;

}