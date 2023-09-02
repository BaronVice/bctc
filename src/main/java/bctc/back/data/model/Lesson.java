package bctc.back.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Entity
@Table(name="lesson_daytime")
public class Lesson {
    @Id
    @GeneratedValue
    public Long id;

    @ManyToOne
    @JoinColumn(name="shedule_id")
    public Schedule schedule;

    @Min(0)
    public int daysIterate;

    public DayOfWeek weekday;
    public LocalTime timeStart;
    public LocalTime timeEnd;
    public boolean isActive;
    public boolean isOnline;
}
