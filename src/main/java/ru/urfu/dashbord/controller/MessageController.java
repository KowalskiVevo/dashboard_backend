package ru.urfu.dashbord.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.urfu.dashbord.dto.MessageDto;
import ru.urfu.dashbord.jpa.entity.Message;
import ru.urfu.dashbord.jpa.entity.Notification;
import ru.urfu.dashbord.jpa.entity.User;
import ru.urfu.dashbord.jpa.repository.MessageRepository;
import ru.urfu.dashbord.jpa.repository.NotificationRepository;
import ru.urfu.dashbord.jpa.repository.UserRepository;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
@Api(tags = "Контроллер управления сообщениями")
public class MessageController {

  private final MessageRepository messageRepository;
  private final NotificationRepository notificationRepository;
  private final UserRepository userRepository;

  @PostMapping()
  @ApiOperation("Создать сообщение")
  public ResponseEntity createMessage(@RequestBody MessageDto messageDto) {
    User userTo = userRepository.findByTag(messageDto.getUserTo().getTag()).orElse(null);
    var message = messageRepository.save(Message.builder()
            .userFrom(userRepository.findByTag(messageDto.getUserFrom().getTag()).orElse(null))
            .userTo(userTo)
            .content(messageDto.getContent())
            .theme(messageDto.getTheme())
        .build());
    notificationRepository.save(Notification.builder()
            .user(userTo)
            .message(message)
            .isActive(Boolean.TRUE)
        .build());
    return ResponseEntity.ok("");
  }
}
