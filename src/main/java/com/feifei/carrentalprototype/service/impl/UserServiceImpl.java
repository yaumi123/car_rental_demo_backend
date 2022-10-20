package com.feifei.carrentalprototype.service.impl;

import com.feifei.carrentalprototype.config.MD5Encryptor;
import com.feifei.carrentalprototype.model.User;
import com.feifei.carrentalprototype.repository.UserRepository;
import com.feifei.carrentalprototype.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  @Autowired UserRepository userRepository;

  @Override
  public User createUser(String name, String nickname, String password) {
    User user = new User(name, nickname, MD5Encryptor.encrypt(password));
    if (userRepository.findByName(name).isPresent()) {
      throw new RuntimeException("Username already exist!");
    }
    userRepository.save(user);
    return user;
  }

  @Override
  public User validate(String token) {
    Optional<User> userOpt = userRepository.findByName(token);
    if (!userOpt.isPresent()) {
      throw new RuntimeException("User not exist!");
    }
    return userOpt.get();
  }

  @Override
  public User login(String name, String password) {
    Optional<User> userOpt = userRepository.findByName(name);
    if (!userOpt.isPresent()) {
      throw new RuntimeException("User not exist!");
    }
    userOpt = userRepository.findByNameAndPassword(name, MD5Encryptor.encrypt(password));
    if (!userOpt.isPresent()) {
      throw new RuntimeException("Password not match!");
    }
    return userOpt.get();
  }
}
