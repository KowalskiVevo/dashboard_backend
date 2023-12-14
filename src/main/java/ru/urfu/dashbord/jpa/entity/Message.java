package ru.urfu.dashbord.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "message")
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_from")
  private User userFrom;

  @ManyToOne
  @JoinColumn(name = "user_to")
  private User userTo;

  private String theme;
  private String content;
}
