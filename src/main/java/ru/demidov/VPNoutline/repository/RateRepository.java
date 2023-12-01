package ru.demidov.VPNoutline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demidov.VPNoutline.entity.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate,Long> {

    Rate findByNameRate (String nameRate);
}
