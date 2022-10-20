package com.feifei.carrentalprototype.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "t_order")
public class Order extends BaseModel {

  @Id private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "m_id")
  private User member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "c_id")
  private CarModel model;

  @Column private boolean active;

  @Column private Date createAt;

  public Order(User member, CarModel model) {
    super();
    this.id = super.id;
    this.member = member;
    this.model = model;
    this.active = true;
    this.createAt = new Date();
  }
}
