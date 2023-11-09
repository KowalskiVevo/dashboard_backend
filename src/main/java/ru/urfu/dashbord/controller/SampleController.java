package ru.urfu.dashbord.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SampleController {

  @GetMapping("/anonymous")
  public String getAnonymousInfo() {
    return "Anonymous";
  }

  @GetMapping("/user")
  @Secured({"USER"})
//  @PreAuthorize("hasRole('USER')")
  public String getUserInfo() {
    return "user info";
  }

  @GetMapping("/admin")
  @Secured({"ADMIN"})
//  @PreAuthorize("hasRole('ADMIN')")
  public String getAdminInfo() {
    return "admin info";
  }

  @GetMapping("/service")
//  @PreAuthorize("hasRole('SERVICE')")
  public String getServiceInfo() {
    return "service info";
  }

  @GetMapping("/me")
  public Object getMe() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }
}