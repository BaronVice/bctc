package bctc.back.data.additions;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Technology {
    @Id
    @GeneratedValue
    public Long id;

    public String name;

    @ManyToMany(mappedBy = "technologies")
    public List<Course> courses;
}
