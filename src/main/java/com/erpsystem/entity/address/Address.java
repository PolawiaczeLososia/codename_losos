package com.erpsystem.entity.address;

import org.springframework.data.annotation.Id;

public class Address {

    @Id
    private String id;
    private String city;
    private String zipCode;
    private String houseNumber;
    private Integer flatNumber;

    private Boolean isActive;

    public Address() {
    }

    public Address(String city, String zipCode, String houseNumber, Integer flatNumber, Boolean isActive) {
        this.city = city;
        this.zipCode = zipCode;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", flatNumber=" + flatNumber +
                ", isActive=" + isActive +
                '}';
    }
}
