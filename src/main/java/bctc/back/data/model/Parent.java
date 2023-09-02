package bctc.back.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Parent extends User{
    @Email
    public String email;

    public String phone;

    @ManyToMany(mappedBy = "parents")
    public List<Student> students;
}
