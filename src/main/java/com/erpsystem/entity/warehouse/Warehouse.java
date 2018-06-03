package com.erpsystem.entity.warehouse;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;

@Document(collection = "warehouse")
public class Warehouse {

  @Id
  private String id;
  private String name;
  private Boolean isActive;

  public Warehouse() {
  }

  public Warehouse(String name, Boolean isActive) {
    this.name = name;
    this.isActive = isActive;
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

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  @Override
  public String toString() {
    return "Warehouse{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", isActive=" + isActive +
        '}';
  }
}
