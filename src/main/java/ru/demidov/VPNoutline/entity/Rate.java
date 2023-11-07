package ru.demidov.VPNoutline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "rates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "name_rates", nullable = false, unique = true)
    @Size(max = 255)
    @NotNull
    private String nameRate;

    @Column (name = "description", nullable = false, updatable = false)
    @Size (max = 255)
    @NotNull
    private String description;

    @Column (name = "price")
    @NotNull
    private Integer price;
}
