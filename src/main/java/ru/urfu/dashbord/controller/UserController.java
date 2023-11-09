package ru.urfu.dashbord.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
  @Secured({"ROLE_ADMIN"})
  void createUser(){
  }

  @Secured({"ROLE_ADMIN"})
  void editUser(){

  }

  @Secured({"ROLE_ADMIN"})
  void deleteUser(){

  }
}
