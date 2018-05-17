package com.erpsystem.entity.carmodel;

import com.erpsystem.entity.car.Car;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="carmodel")
public class CarModel {

    @Id
    private String id;
    private String name;
    private String productionYear;

    private Car car;

    public CarModel(String name, String productionYear, Car car) {
        this.name = name;
        this.productionYear = productionYear;
        this.car = car;
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

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", productionYear='" + productionYear + '\'' +
                ", car=" + car +
                '}';
    }
}

