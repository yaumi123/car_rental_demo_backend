package com.feifei.carrentalprototype.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.feifei.carrentalprototype.TestUtil;
import com.feifei.carrentalprototype.model.User;
import com.feifei.carrentalprototype.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

  private MockMvc mvc;

  @Mock private UserService userService;

  @InjectMocks private UserController userController;

  @Before
  public void setup() {
    JacksonTester.initFields(this, new ObjectMapper());
    mvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  public void testCreateUserSuccess() throws Exception {
    String mockName = "test";
    String mockNickname = "testNick";
    String mockPassword = "testPsw";
    User mockUser = new User(mockName, mockNickname, mockPassword);
    // when
    MockHttpServletResponse response =
        mvc.perform(
                post("/user/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.dumps(mockUser, PropertyNamingStrategies.SNAKE_CASE)))
            .andReturn()
            .getResponse();

    // assert
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  public void testLoginSuccess() throws Exception {
    String mockName = "test";
    String mockPassword = "testPsw";
    User mockUser = new User(mockName, "mockNickname", mockPassword);

    // when
    MockHttpServletResponse response =
        mvc.perform(
                post("/user/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.dumps(mockUser, PropertyNamingStrategies.SNAKE_CASE)))
            .andReturn()
            .getResponse();

    // assert
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }
}
