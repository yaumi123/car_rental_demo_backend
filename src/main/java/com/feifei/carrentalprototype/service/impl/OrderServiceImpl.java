package com.feifei.carrentalprototype.service.impl;

import com.feifei.carrentalprototype.model.CarModel;
import com.feifei.carrentalprototype.model.Order;
import com.feifei.carrentalprototype.model.User;
import com.feifei.carrentalprototype.repository.OrderRepository;
import com.feifei.carrentalprototype.service.CarModelService;
import com.feifei.carrentalprototype.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
  @Autowired OrderRepository orderRepository;
  @Autowired CarModelService carModelService;

  @Override
  public Order createOrder(User user, CarModel model) {
    if (model.getNum() < 1) {
      throw new RuntimeException("Car model number not enough!");
    }
    Order order = new Order(user, model);
    carModelService.changeStorage(model.getId(), -1);
    orderRepository.save(order);
    return order;
  }

  @Override
  public List<Order> getUserOrders(User user) {
    return orderRepository.findAllByMember(user);
  }

  @Override
  public boolean cancelOrder(String id) {
    Optional<Order> orderOpt = orderRepository.findById(id);
    if (orderOpt.isPresent()) {
      Order order = orderOpt.get();
      order.setActive(false);
      carModelService.changeStorage(order.getModel().getId(), 1);
      return true;
    }
    return false;
  }
}
