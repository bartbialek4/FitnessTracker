package pl.wsb.fitnesstracker.statistics.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

@Entity
@Table(name = "statistics") // Dobra praktyka: małe litery w nazwach tabel
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne // Relacja 1:1
    @JoinColumn(name = "user_id", unique = true) // Klucz obcy user_id, musi być unikalny dla 1:1
    private User user;

    @Column(name = "total_trainings")
    private int totalTrainings;

    @Column(name = "total_distance")
    private double totalDistance;

    @Column(name = "total_calories_burned")
    private int totalCaloriesBurned;

    public Statistics(
            final User user,
            final int totalTrainings,
            final double totalDistance,
            final int totalCaloriesBurned) {
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}