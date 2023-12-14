package ru.urfu.dashbord.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.urfu.dashbord.jpa.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
