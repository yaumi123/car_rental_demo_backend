package com.feifei.carrentalprototype.controller;

import com.feifei.carrentalprototype.config.Resp;
import com.feifei.carrentalprototype.model.CarModel;
import com.feifei.carrentalprototype.model.Order;
import com.feifei.carrentalprototype.model.User;
import com.feifei.carrentalprototype.model.dto.OrderDTO;
import com.feifei.carrentalprototype.service.CarModelService;
import com.feifei.carrentalprototype.service.OrderService;
import com.feifei.carrentalprototype.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
  @Autowired OrderService orderService;
  @Autowired CarModelService carModelService;
  @Autowired UserService userService;

  @PostMapping("/place")
  public Resp<OrderDTO> placeOrder(@RequestParam String token, @RequestParam String modelId) {
    try {
      User user = userService.validate(token);
      CarModel carModel = carModelService.getCarModel(modelId);
      Order order = orderService.createOrder(user, carModel);
      return Resp.of(OrderDTO.createBy(order));
    } catch (Exception e) {
      return Resp.error(e.getMessage());
    }
  }

  @GetMapping("/cancel")
  public Resp<Void> cancelOrder(@RequestParam(name = "order") String orderId) {
    try {
      orderService.cancelOrder(orderId);
      return Resp.of(null);
    } catch (Exception e) {
      return Resp.error(e.getMessage());
    }
  }

  @GetMapping("/user")
  public Resp<List<OrderDTO>> getUserOrders(@RequestParam String token) {
    try {
      User user = userService.validate(token);
      return Resp.of(
          orderService.getUserOrders(user).stream()
              .map(OrderDTO::createBy)
              .collect(Collectors.toList()));
    } catch (Exception e) {
      return Resp.error(e.getMessage());
    }
  }

  @GetMapping("/models")
  public Resp<List<CarModel>> getModels() {
    try {
      return Resp.of(carModelService.getModelList());
    } catch (Exception e) {
      return Resp.error(e.getMessage());
    }
  }
}
