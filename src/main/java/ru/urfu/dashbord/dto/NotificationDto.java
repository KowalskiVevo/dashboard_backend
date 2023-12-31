package ru.urfu.dashbord.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDto {
  private Boolean isActive;
  private MessageDto message;
  private UserDto user;
}
