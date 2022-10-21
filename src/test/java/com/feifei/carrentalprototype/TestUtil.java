package com.feifei.carrentalprototype;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;
import java.util.List;

public class TestUtil {

  public static <T> String dumps(T data, PropertyNamingStrategy namingStrategy) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(namingStrategy);
    try {
      return objectMapper.writeValueAsString(data);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Json serialize failed", e);
    }
  }

  public static <T> T loads(String jsonString, Class<T> tClass) {
    return loads(jsonString, tClass, PropertyNamingStrategy.LOWER_CAMEL_CASE);
  }

  public static <T> T loads(
          String jsonString, Class<T> tClass, PropertyNamingStrategy namingStrategy) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.setPropertyNamingStrategy(namingStrategy);
    try {
      return objectMapper.readValue(jsonString, tClass);
    } catch (IOException e) {
      throw new RuntimeException("Json deserialize failed", e);
    }
  }
}
