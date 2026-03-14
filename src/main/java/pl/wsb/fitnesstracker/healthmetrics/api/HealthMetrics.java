package pl.wsb.fitnesstracker.healthmetrics.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
    @Table(name = "healthmetrics")
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    public class HealthMetrics {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Nullable
        private Long id;

        @Column(name = "user_id", nullable = false)
        private int user_id;

        @Column(name = "date")
        private Date date;

        @Column(name = "weight")
        private float weight;

        @Column(name = "height")
        private float height;

        @Column(name = "heartrate")
        private int heartRate;

    public HealthMetrics(int user_id, Date date, float weight, float height, int heartRate) {
        this.user_id = user_id;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
    }

}
