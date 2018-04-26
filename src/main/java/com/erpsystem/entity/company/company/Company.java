package com.erpsystem.entity.company.company;

import com.erpsystem.entity.simple_item.simple_item.SimpleItem;
import com.erpsystem.entity.complex_item.complex_item.ComplexItem;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="company")
public class Company {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="street")
    private String street;

    @Column(name="number")
    private Integer number;

    @Column(name="flat_number")
    private Integer flatNumber;

    @Column(name="NIP")
    private Integer NIP;

    @JsonBackReference
    @OneToMany(mappedBy="provider",
                fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.REFRESH, CascadeType.PERSIST})
    private List<SimpleItem> simpleItems;

    @JsonBackReference
    @OneToMany(mappedBy="provider",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.REFRESH, CascadeType.PERSIST})
    private List<ComplexItem> complexItems;

    public Company() {
    }

    public Company(String name, String street, Integer number, Integer flatNumber, Integer NIP) {
        this.name = name;
        this.street = street;
        this.number = number;
        this.flatNumber = flatNumber;
        this.NIP = NIP;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    public Integer getNIP() {
        return NIP;
    }

    public void setNIP(Integer NIP) {
        this.NIP = NIP;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", flatNumber=" + flatNumber +
                ", NIP=" + NIP +
                '}';
    }
}
