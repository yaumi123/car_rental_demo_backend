package com.feifei.carrentalprototype.service;

import com.feifei.carrentalprototype.model.CarModel;
import com.feifei.carrentalprototype.model.Order;
import com.feifei.carrentalprototype.model.User;

import java.util.List;

public interface OrderService {

  Order createOrder(User user, CarModel model);

  List<Order> getUserOrders(User user);

  boolean cancelOrder(String id);
}
