package ru.demidov.VPNoutline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demidov.VPNoutline.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
}
