package ru.urfu.dashbord.mapper;

import org.springframework.stereotype.Component;
import ru.urfu.dashbord.dto.MessageDto;
import ru.urfu.dashbord.jpa.entity.Message;

@Component
public class MessageMapper {
  public static MessageDto toDto(Message message) {
    return MessageDto.builder()
        .userTo(UserMapper.toDto(message.getUserTo()))
        .userFrom(UserMapper.toDto(message.getUserFrom()))
        .content(message.getContent())
        .theme(message.getTheme())
        .build();
  }
}
