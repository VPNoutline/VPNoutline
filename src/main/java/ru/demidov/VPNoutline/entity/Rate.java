package ru.demidov.VPNoutline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Table(name = "rates")
@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_rates", nullable = false)
    @Size(max = 255)
    private String nameRate;

    @Column(name = "description", nullable = false)
    @Size(max = 255)
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;
}
