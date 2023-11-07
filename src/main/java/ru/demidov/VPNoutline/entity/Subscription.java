package ru.demidov.VPNoutline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table (name = "subscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "enabled_start_rate")
    @NotNull
    private boolean enabledStartRate;

    @Column (name = "enabled_rate")
    @NotNull
    private boolean enabledRate;

    @CreationTimestamp
    @Column(name = "created", nullable = false)
    @NotNull
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated", nullable = false)
    @NotNull
    private LocalDateTime updated;

    @Column(name = "finished", nullable = false)
    @NotNull
    private LocalDateTime finished;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rates_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Rate rates;
}
