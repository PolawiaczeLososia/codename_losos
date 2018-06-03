package com.erpsystem.entity.car;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "car")
public class Car {

  @Id
  private String id;
  private String name;

  public Car() {
  }

  public Car(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Car{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
