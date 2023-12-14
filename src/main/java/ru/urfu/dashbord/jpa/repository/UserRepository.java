package ru.urfu.dashbord.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.urfu.dashbord.jpa.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByTag(String tag);
}

