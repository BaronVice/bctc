package bctc.back.data.additions;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
