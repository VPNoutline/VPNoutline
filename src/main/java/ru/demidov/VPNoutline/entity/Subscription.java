package ru.demidov.VPNoutline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "subscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "key_access", nullable = false, unique = true)
    @Size(max = 255)
    @NotNull
    private String keyAccess;

    @Column(name = "enabled_start_rate")
    @NotNull
    private boolean enabledStartRate;

    @Column(name = "enabled_rate")
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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subscriptions")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<User> users;
}
