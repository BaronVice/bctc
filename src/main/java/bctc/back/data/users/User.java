package bctc.back.data.users;

import bctc.back.data.credentials.Credentials;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "credentials_id")
    @JsonIgnore
    protected Credentials credentials;

    protected String first_name;

    protected String last_name;
}