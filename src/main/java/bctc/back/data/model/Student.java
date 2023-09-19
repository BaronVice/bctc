package bctc.back.data.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student extends User {
    public Student(String username, String password){
        this.setAccountPassword(password);
        this.setUsername(username);
    }

    @OneToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    public School school;

    public LocalDate birthdate;

    // todo IDK how to store NULL or number instead of 0 or number
    @Min(0)
    @Max(11)
    public int grade_number;

    @Pattern(regexp = "[А-Я]")
    public String grade_letter;

    @ManyToMany
    @JoinTable(
            name = "parent_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id")
    )
    public List<Parent> parents;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    public List<Course> courses;

    @ManyToMany
    @JoinTable(
            name = "student_group",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    public List<Group> groups;

    @ManyToMany
    @JoinTable(
            name = "student_event",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    public List<Event> events;
}
