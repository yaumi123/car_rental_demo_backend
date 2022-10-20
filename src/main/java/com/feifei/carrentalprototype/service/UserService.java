package com.feifei.carrentalprototype.service;

import com.feifei.carrentalprototype.model.User;

public interface UserService {
  User createUser(String name, String nickName, String password);

  User validate(String token);

  User login(String name, String password);
}
