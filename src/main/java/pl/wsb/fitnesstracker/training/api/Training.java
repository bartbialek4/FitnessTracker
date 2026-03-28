package pl.wsb.fitnesstracker.training.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;

@Entity
@Table(name = "trainings") // Ustawienie nazwy tabeli na "trainings"
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA wymaga bezargumentowego konstruktora
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Relacja Many-to-One: wiele treningów do jednego użytkownika
    @JoinColumn(name = "user_id") // Nazwa kolumny klucza obcego w tabeli trainings
    private User user;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Enumerated(EnumType.STRING) // Mapowanie enuma jako String w bazie
    @Column(name = "activity_type")
    private ActivityType activityType;

    @Column(name = "distance")
    private double distance;

    @Column(name = "average_speed")
    private double averageSpeed;

    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}