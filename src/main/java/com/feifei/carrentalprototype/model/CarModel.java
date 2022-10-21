package com.feifei.carrentalprototype.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "car_model")
@NoArgsConstructor
@AllArgsConstructor
public class CarModel extends BaseModel {

  @Id private String id;

  @Column private String model;

  @Column private int num;
}
