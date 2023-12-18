package ru.urfu.dashbord.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.urfu.dashbord.jpa.entity.Notification;
import ru.urfu.dashbord.jpa.entity.User;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
  List<Notification> findByUser(User user);
}
