package bctc.back.data.additions;

import bctc.back.data.users.student.Student;
import bctc.back.data.users.Tutor;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "individual_lesson")    // todo maybe call it "private lessons"?
public class IndividualLesson {
    @Id
    @GeneratedValue
    public Long id;

    @ManyToOne
    @JoinColumn(name="tutor_id", nullable=false)
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name="student_id", nullable=false)
    private Student student;

    public LocalDateTime startDatetime;
    public LocalDateTime endDatetime;

    public boolean isHoliday;
    public boolean isWeekend;
    public boolean isOffline;
    public boolean isCancelled;

}
