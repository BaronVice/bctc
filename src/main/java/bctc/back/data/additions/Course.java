package bctc.back.data.additions;


import bctc.back.data.users.student.Student;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue
    public Long id;

    public String name;
    public String description;
    public int hours;

    @ManyToMany(mappedBy = "courses")
    public List<Student> students;

    @ManyToMany
    @JoinTable(
            name = "course_technology",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    public List<Technology> technologies;
}
