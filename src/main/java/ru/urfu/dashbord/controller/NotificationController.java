package ru.urfu.dashbord.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.urfu.dashbord.dto.NotificationDto;
import ru.urfu.dashbord.jpa.repository.NotificationRepository;
import ru.urfu.dashbord.jpa.repository.UserRepository;
import ru.urfu.dashbord.mapper.NotificationMapper;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@Api(tags = "Контроллер управления уведомлениями")
public class NotificationController {
  private final NotificationRepository notificationRepository;
  private final UserRepository userRepository;

  @GetMapping()
  @ApiOperation("Получить все уведомления для определенного юзера")
  public ResponseEntity<List<NotificationDto>> getNotificationsByUserTag(@RequestParam String userTag) {
    return ResponseEntity.ok(notificationRepository.findByUser(userRepository.findByTag(userTag).orElse(null))
        .stream()
        .map(NotificationMapper::toDto)
        .toList());
  }
}
