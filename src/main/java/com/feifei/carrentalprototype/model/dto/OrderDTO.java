package com.feifei.carrentalprototype.model.dto;

import com.feifei.carrentalprototype.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

  public String id;

  public String user;

  public String carModel;

  public Date createAt;

  public Boolean active;

  public static OrderDTO createBy(Order order) {
    return new OrderDTO(
        order.getId(),
        order.getMember().getNickname(),
        order.getModel().getModel(),
        order.getCreateAt(),
        order.isActive());
  }
}
