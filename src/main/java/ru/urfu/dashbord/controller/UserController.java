package ru.urfu.dashbord.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.urfu.dashbord.dto.UserDto;
import ru.urfu.dashbord.jpa.entity.User;
import ru.urfu.dashbord.jpa.repository.UserRepository;
import ru.urfu.dashbord.mapper.UserMapper;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(tags = "Контроллер управления данными пользователя")
@Slf4j
public class UserController {
  private final UserRepository userRepository;

  @GetMapping("/me")
  @ApiOperation("Получить информацию об авторизованном пользователе")
  public ResponseEntity getMe() {
    return userRepository.findByTag(SecurityContextHolder.getContext().getAuthentication().getName())
        .map(UserMapper::toDto)
        .map(ResponseEntity::ok)
        .orElse(null);
  }

  @PutMapping("/user")
  @ApiOperation("Редактирование пользователя")
  public ResponseEntity putUser(@RequestBody UserDto userDto,
                                @RequestParam(required = false) MultipartFile photo) {
    try {
      return userRepository.findByTag(userDto.getTag())
          .map(User::getId)
          .map(id -> {
            try {
              return saveUser(id, photo, userDto);
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

  private User saveUser(Long id, MultipartFile photo, UserDto userDto) throws IOException {
    User user = UserMapper.toEntity(userDto);
    if (Objects.nonNull(photo)) {
      user.setPhoto(photo.getBytes());
    }
    user.setId(id);
    return userRepository.save(user);
  }
}
