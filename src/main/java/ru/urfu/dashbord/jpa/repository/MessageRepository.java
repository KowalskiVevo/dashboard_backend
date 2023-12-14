package ru.urfu.dashbord.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.urfu.dashbord.jpa.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}

