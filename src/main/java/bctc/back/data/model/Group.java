package bctc.back.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "_group")
public class Group {
    @Id
    @GeneratedValue
    public Long id;

    public String name;

    public String chatUrl;

    @ManyToMany(mappedBy = "groups")
    public List<Tutor> tutors;

    @ManyToMany(mappedBy = "groups")
    public List<Student> students;

    @ManyToMany
    @JoinTable(
            name = "group_event",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    public List<Event> events;

    public boolean isActive;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    public Schedule schedule;
}
