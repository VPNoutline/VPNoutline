package ru.demidov.VPNoutline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "users")
@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(min = 2, max = 255)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_subscriptions",
                joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "subscription_id", referencedColumnName = "id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Subscription subscription;
}
