package ru.urfu.dashbord.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String tag;
  private String lastname;
  private String firstname;
  private String middlename;
  private LocalDate birthdate;
  private byte[] photo;
  private String about;

  @ManyToOne
  @JoinColumn(name = "id_role")
  private Role role;
}
