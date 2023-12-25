package ru.urfu.dashbord.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.urfu.dashbord.dto.UserDto;
import ru.urfu.dashbord.jpa.entity.User;
import ru.urfu.dashbord.jpa.repository.UserRepository;
import ru.urfu.dashbord.mapper.UserMapper;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(tags = "Контроллер управления данными пользователя")
@Slf4j
public class UserController {
  private final UserRepository userRepository;

  @GetMapping("/me")
  @ApiOperation("Получить информацию об авторизованном пользователе")
  public ResponseEntity<UserDto> getMe() {
    Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return userRepository.findByTag(jwt.getClaims().get("preferred_username").toString())
        .map(UserMapper::toDto)
        .map(ResponseEntity::ok)
        .orElse(null);
  }

  @PutMapping("/user")
  @ApiOperation("Редактирование пользователя")
  public ResponseEntity putUser(@RequestBody UserDto userDto) {
    try {
      return userRepository.findByTag(userDto.getTag())
          .map(user -> {
            try {
              return saveUser(user, userDto);
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          })
          .map(user -> ResponseEntity.ok((Object) UserMapper.toDto(user)))
          .orElse(ResponseEntity.badRequest().body("Пользователь не найден в системе"));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @GetMapping("/all")
  @ApiOperation("Получить всех пользователей")
  public ResponseEntity<List<UserDto>> getAllUser(){
    List<UserDto> userDtos = userRepository.findAll().stream().map(UserMapper::toDto).toList();
    return ResponseEntity.ok(userDtos);
  }

  private User saveUser(User user, UserDto userDto) throws IOException {
    if (Objects.nonNull(userDto.getPhoto())) {
      user.setPhoto(userDto.getPhoto());
    }
    user.setBirthdate(userDto.getBirthdate());
    user.setFirstname(userDto.getFirstname());
    user.setLastname(userDto.getLastname());
    user.setAbout(userDto.getAbout());
    return userRepository.save(user);
  }
}
