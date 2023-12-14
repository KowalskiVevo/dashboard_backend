package ru.urfu.dashbord.mapper;

import org.springframework.stereotype.Component;
import ru.urfu.dashbord.dto.UserDto;
import ru.urfu.dashbord.jpa.entity.Role;
import ru.urfu.dashbord.jpa.entity.User;

import java.util.Objects;
import java.util.Optional;

@Component
public class UserMapper {
  public static UserDto toDto(User user) {
    return UserDto.builder()
        .tag(user.getTag())
        .lastname(user.getLastname())
        .firstname(user.getFirstname())
        .middlename(user.getMiddlename())
        .birthdate(user.getBirthdate())
        .photo(user.getPhoto())
        .about(user.getAbout())
        .roleAlias(Optional.ofNullable(user.getRole()).map(Role::getAlias).orElse(null))
        .roleName(Optional.ofNullable(user.getRole()).map(Role::getRoleName).orElse(null))
        .build();
  }

  public static User toEntity(UserDto userDto){
    return User.builder()
        .tag(userDto.getTag())
        .lastname(userDto.getLastname())
        .firstname(userDto.getFirstname())
        .middlename(userDto.getMiddlename())
        .birthdate(userDto.getBirthdate())
        .photo(userDto.getPhoto())
        .about(userDto.getAbout())
        .role(Optional.ofNullable(userDto.getRoleAlias())
            .filter(u -> Objects.nonNull(userDto.getRoleName()))
            .map(r -> Role.builder()
                .alias(userDto.getRoleAlias())
                .roleName(userDto.getRoleName())
                .build())
            .orElse(null))
        .build();
  }
}
