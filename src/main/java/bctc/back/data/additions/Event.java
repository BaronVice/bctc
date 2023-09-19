package bctc.back.data.additions;

import bctc.back.data.users.student.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue
    public Long id;

    public LocalDate scheduledAt;
    public boolean isOnline;
    public boolean isOffline;

    @ManyToMany(mappedBy = "events")
    public List<Student> students;

    @ManyToMany(mappedBy = "events")
    public List<Group> groups;
}
