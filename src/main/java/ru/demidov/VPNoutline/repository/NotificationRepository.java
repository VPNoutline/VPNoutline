package ru.demidov.VPNoutline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demidov.VPNoutline.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
