package com.feifei.carrentalprototype.repository;

import com.feifei.carrentalprototype.model.Order;
import com.feifei.carrentalprototype.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
  List<Order> findAllByMember(User user);
}
