package ru.urfu.dashbord.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.urfu.dashbord.jpa.entity.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
  Optional<Users> findByTag(String tag);
}
