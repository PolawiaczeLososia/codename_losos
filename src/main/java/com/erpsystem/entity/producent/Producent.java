package com.erpsystem.entity.producent;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "producent")
public class Producent {

  @Id
  private String id;
  private String name;

  public Producent() {
  }

  public Producent(String name) {
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
    return "Producent{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
