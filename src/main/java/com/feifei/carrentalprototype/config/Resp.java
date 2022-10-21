package com.feifei.carrentalprototype.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Resp<T> {
  int code;
  String message;
  T data;

  public static <T> Resp<T> of(T data) {
    return new Resp<>(200, "ok", data);
  }

  public static Resp error(String message) {
    return new Resp(400, message, null);
  }
}
