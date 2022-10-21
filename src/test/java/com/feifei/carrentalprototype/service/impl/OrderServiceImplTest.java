package com.feifei.carrentalprototype.service.impl;

import com.feifei.carrentalprototype.model.CarModel;
import com.feifei.carrentalprototype.model.Order;
import com.feifei.carrentalprototype.model.User;
import com.feifei.carrentalprototype.repository.CarModelRepository;
import com.feifei.carrentalprototype.repository.OrderRepository;
import com.feifei.carrentalprototype.service.CarModelService;
import com.feifei.carrentalprototype.service.OrderService;
import com.feifei.carrentalprototype.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ActiveProfiles("linux")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@Transactional
public class OrderServiceImplTest {
  @Autowired OrderService orderService;
  @Autowired CarModelService carModelService;
  @Autowired CarModelRepository carModelRepository;
  @Autowired OrderRepository orderRepository;
  @Autowired UserService userService;
  CarModel carModel;
  User user;

  @Before
  public void init() {
    this.user = userService.createUser("test", "nick", "psw");
    this.carModel = carModelRepository.save(new CarModel("test", "Test Model", 1));
  }

  @Test
  public void testCreateOrder() {
    CarModel carModel2 = carModelService.getCarModel(this.carModel.getId());
    int numBefore = carModel2.getNum();
    Assert.assertEquals(1, numBefore);
    Order order = orderService.createOrder(this.user, this.carModel);
    Assert.assertEquals(this.user.getId(), order.getMember().getId());
    Assert.assertEquals(this.carModel.getId(), order.getModel().getId());
    Assert.assertEquals(numBefore - 1, order.getModel().getNum());
    Assert.assertThrows(
        RuntimeException.class, () -> orderService.createOrder(this.user, this.carModel));
  }

  @Test
  public void testCancelOrder() {
    Order order = orderService.createOrder(this.user, this.carModel);
    Assert.assertTrue(order.isActive());
    CarModel carModel2 = carModelService.getCarModel(this.carModel.getId());
    Assert.assertEquals(0, carModel2.getNum());
    orderService.cancelOrder(order.getId());
    Assert.assertFalse(orderRepository.findById(order.getId()).get().isActive());
    carModel2 = carModelService.getCarModel(this.carModel.getId());
    Assert.assertEquals(1, carModel2.getNum());
  }
}
