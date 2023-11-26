package ru.demidov.VPNoutline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demidov.VPNoutline.entity.Subscription;
import ru.demidov.VPNoutline.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    User findByEmail(String email);

    List<User> findBySubscription(Subscription subscription);
}
