package com.feifei.carrentalprototype.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feifei.carrentalprototype.model.User;
import com.feifei.carrentalprototype.service.OrderService;
import com.feifei.carrentalprototype.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

  private MockMvc mvc;

  @Mock private OrderService orderService;

  @Mock private UserService userService;

  @InjectMocks private OrderController orderController;

  @Before
  public void setup() {
    JacksonTester.initFields(this, new ObjectMapper());
    mvc = MockMvcBuilders.standaloneSetup(orderController).build();
  }

  @Test
  public void testGetUserOrdersSuccess() throws Exception {
    String mockName = "test";
    String mockPassword = "testPsw";
    User mockUser = new User(mockName, "mockNickname", mockPassword);
    doReturn(new ArrayList<>()).when(orderService).getUserOrders(any());
    doReturn(mockUser).when(userService).validate(anyString());

    // when
    MockHttpServletResponse response =
        mvc.perform(get("/order/user").param("token", "testToken")).andReturn().getResponse();

    // assert
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  public void testCancelOrderSuccess() throws Exception {
    doReturn(true).when(orderService).cancelOrder(any());

    // when
    MockHttpServletResponse response =
        mvc.perform(get("/order/cancel").param("order", "test-order-id"))
            .andReturn()
            .getResponse();

    // assert
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }
}
