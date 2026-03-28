package pl.wsb.fitnesstracker.healthmetrics.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User; // Import Twojego modelu użytkownika

import java.util.Date;

@Entity
@Table(name = "health_metrics") // Zmieniono na snake_case zgodnie z prośbą
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class HealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Usunięto @Nullable - id będzie generowane automatycznie

    @ManyToOne // Relacja Many-to-One: wiele pomiarów do jednego użytkownika
    @JoinColumn(name = "user_id", nullable = false) // Mapowanie klucza obcego
    private User user; // Zmieniono z int user_id na obiekt User

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "weight")
    private float weight;

    @Column(name = "height")
    private float height;

    @Column(name = "heart_rate") // Poprawiono nazwę kolumny na czytelniejszą
    private int heartRate;

    public HealthMetrics(
            final User user,
            final Date date,
            final float weight,
            final float height,
            final int heartRate) {
        this.user = user;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
    }
}