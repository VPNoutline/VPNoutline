package ru.demidov.VPNoutline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message", nullable = false)
    @Size(max = 255)
    private String message;

    @CreationTimestamp
    @Column(name = "sent_date_time", nullable = false)
    private LocalDateTime dateTime;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private User user;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "subscription_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Subscription subscription;

}
