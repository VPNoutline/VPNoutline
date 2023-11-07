package ru.demidov.VPNoutline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "mails_case")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailCase {
    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, updatable = false)
    @NotBlank
    @Size(min = 2, max = 255)
    private String email;

    @CreationTimestamp
    @Column(name = "date_time", nullable = false, updatable = false)
    @NotNull
    private LocalDateTime dateTime;

    @Override
    public String toString() {
        return "MailCase{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
