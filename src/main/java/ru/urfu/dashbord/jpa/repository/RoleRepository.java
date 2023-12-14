package ru.urfu.dashbord.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.urfu.dashbord.jpa.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
