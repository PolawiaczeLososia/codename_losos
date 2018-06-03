package com.erpsystem.entity.product;

import com.erpsystem.entity.carmodel.CarModel;
import com.erpsystem.entity.price.Price;
import com.erpsystem.entity.producent.Producent;
import com.erpsystem.entity.warehouse.Warehouse;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "product")
public class Product {

  @Id
  private String id;
  private String name;
  private Integer quantity;
  private Boolean isActive;
  private String description;

  private Warehouse warehouse;
  private Producent producent;
  private List<CarModel> carModels;
  private List<Price> prices;

  public Product() {
  }

  public Product(String name, Integer quantity, Boolean isActive, String description,
      Warehouse warehouse, Producent producent,
      List<CarModel> carModels, List<Price> prices) {
    this.name = name;
    this.quantity = quantity;
    this.isActive = isActive;
    this.description = description;
    this.warehouse = warehouse;
    this.producent = producent;
    this.carModels = carModels;
    this.prices = prices;
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

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Warehouse getWarehouse() {
    return warehouse;
  }

  public void setWarehouse(Warehouse warehouse) {
    this.warehouse = warehouse;
  }

  public Producent getProducent() {
    return producent;
  }

  public void setProducent(Producent producent) {
    this.producent = producent;
  }

  public List<CarModel> getCarModels() {
    return carModels;
  }

  public void setCarModels(List<CarModel> carModels) {
    this.carModels = carModels;
  }

  public List<Price> getPrices() {
    return prices;
  }

  public void setPrices(List<Price> prices) {
    this.prices = prices;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", quantity=" + quantity +
        ", isActive=" + isActive +
        ", description='" + description + '\'' +
        ", warehouse=" + warehouse +
        ", producent=" + producent +
        ", carModels=" + carModels +
        ", prices=" + prices +
        '}';
  }
}

