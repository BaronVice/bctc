package bctc.back.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class School {
    @Id
    @GeneratedValue
    public Long id;

    public String name;
}
