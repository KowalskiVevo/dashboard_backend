package ru.urfu.dashbord.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {
  @Id
  private Long id;
  @Column(name = "tag")
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
