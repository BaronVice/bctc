package bctc.back.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Tutor extends User{
    @Email
    public String email;

    public String phone;

    @ManyToMany
    @JoinTable(
            name = "tutor_group",
            joinColumns = @JoinColumn(name = "tutor_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    public List<Group> groups;

}
