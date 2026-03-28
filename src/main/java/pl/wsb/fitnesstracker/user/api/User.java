package pl.wsb.fitnesstracker.user.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.healthmetrics.api.HealthMetrics; // Nowy import
import pl.wsb.fitnesstracker.statistics.api.Statistics;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"trainings", "statistics", "healthMetrics"}) // Dodano healthMetrics do wykluczeń
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(unique = true)
    private String email;

    // 1:N do treningów
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Training> trainings = new ArrayList<>();

    // 1:1 do statystyk
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Statistics statistics;

    // NOWA RELACJA: 1:N do pomiarów zdrowotnych
    // Użytkownik może mieć wiele wpisów dotyczących wagi/tętna w czasie
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthMetrics> healthMetrics = new ArrayList<>();

    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthday,
            final String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
    }
}