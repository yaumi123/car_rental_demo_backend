package com.feifei.carrentalprototype.controller;

import com.feifei.carrentalprototype.config.Resp;
import com.feifei.carrentalprototype.model.User;
import com.feifei.carrentalprototype.service.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Autowired UserService userService;

  @PostMapping("/register")
  public Resp<User> register(@RequestBody UserDTO userDTO) {
    try {
      return Resp.of(
          userService.createUser(
              userDTO.getUsername(), userDTO.getNickname(), userDTO.getPassword()));
    } catch (Exception e) {
      return Resp.error(e.getMessage());
    }
  }

  @PostMapping("/login")
  public Resp<User> login(@RequestBody UserDTO user) {
    try {
      return Resp.of(userService.login(user.getUsername(), user.getPassword()));
    } catch (Exception e) {
      log.warn(e.getMessage());
      return Resp.error(e.getMessage());
    }
  }

  @Getter
  @Setter
  public static class UserDTO {
    String username;
    String nickname;
    String password;
  }
}
