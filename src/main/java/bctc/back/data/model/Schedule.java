package bctc.back.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue
    public Long id;

    public LocalDate startDate;

    // todo or put here endDate and compute activeness status?
    public boolean isActive;

    @OneToMany
    public List<Lesson> lessons;
}
