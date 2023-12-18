package ru.urfu.dashbord.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.urfu.dashbord.jpa.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByTag(String tag);

  @Query(value = "select * from {h-schema}users u order by (CAST(concat(EXTRACT(year from now()), '-'," +
                 " EXTRACT(month from u.birthdate), '-', EXTRACT(day from u.birthdate)) as timestamptz) >= now())",
      nativeQuery = true)
  List<User> findAllOrderByBirthdate();
}

