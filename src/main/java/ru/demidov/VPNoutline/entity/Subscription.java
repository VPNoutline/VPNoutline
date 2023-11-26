package ru.demidov.VPNoutline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subscriptions")
@Setter
@Getter
@Accessors(chain = true)
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
    private String keyAccess;

    @Column(name = "enabled_rate", nullable = false)
    private boolean enabledRate;

    @CreationTimestamp
    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "finished", nullable = false)
    private LocalDateTime finished;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "rates_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Rate rates;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "subscription", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<User> users = new ArrayList<>();
}
