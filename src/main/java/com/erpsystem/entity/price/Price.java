package com.erpsystem.entity.price;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "price")
public class Price {

  @Id
  private String id;
  private Integer sellPrice;
  private Integer buyPrice;
  private Date date;

  public Price() {
  }

  public Price(Integer sellPrice, Integer buyPrice, Date date) {
    this.sellPrice = sellPrice;
    this.buyPrice = buyPrice;
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(Integer sellPrice) {
    this.sellPrice = sellPrice;
  }

  public Integer getBuyPrice() {
    return buyPrice;
  }

  public void setBuyPrice(Integer buyPrice) {
    this.buyPrice = buyPrice;
  }

  public Date getDateTime() {
    return date;
  }

  public void setDateTime(Date date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "Price{" +
        "id='" + id + '\'' +
        ", sellPrice=" + sellPrice +
        ", buyPrice=" + buyPrice +
        ", date=" + date +
        '}';
  }
}
