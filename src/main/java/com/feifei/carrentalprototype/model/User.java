package com.feifei.carrentalprototype.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "member")
public class User extends BaseModel {

  @Id private String id;

  @Column(name = "m_name")
  private String name;

  @Column private String nickname;

  @Column private String password;

  @Column private Date createAt;

  public User(String name, String nickname, String password) {
    super();
    this.id = super.id;
    this.name = name;
    this.nickname = nickname;
    this.password = password;
    this.createAt = new Date();
  }
}
