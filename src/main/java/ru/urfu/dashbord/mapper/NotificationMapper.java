package ru.urfu.dashbord.mapper;

import org.springframework.stereotype.Component;
import ru.urfu.dashbord.dto.NotificationDto;
import ru.urfu.dashbord.jpa.entity.Notification;

@Component
public class NotificationMapper {
  public static NotificationDto toDto(Notification notification){
    return NotificationDto.builder()
        .user(UserMapper.toDto(notification.getUser()))
        .message(MessageMapper.toDto(notification.getMessage()))
        .isActive(notification.getIsActive())
        .build();
  }
}
