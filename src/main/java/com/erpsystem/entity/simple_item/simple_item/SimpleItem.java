package com.erpsystem.entity.simple_item.simple_item;

import com.erpsystem.entity.company.company.Company;
import com.erpsystem.entity.complex_item.complex_item.ComplexItem;
import com.erpsystem.entity.simple_item.detail.SimpleItemDetail;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.erpsystem.global.Unit;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="simple_item")
public class SimpleItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="netto_value")
    private float nettoValue;

    @Column(name="stock")
    private float stock;

    @Enumerated(EnumType.STRING)
    @Column(name="unit")
    private Unit unit;

    @OneToOne(fetch=FetchType.LAZY,
              cascade=CascadeType.ALL)
    @JoinColumn(name="detail_id")
    //@RestResource(path="detail", rel="simpleItemDetail")
    private SimpleItemDetail detail;

    @JsonBackReference
    @ManyToMany(fetch=FetchType.LAZY,
                cascade = {CascadeType.MERGE, CascadeType.DETACH,
                            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="simple_complex",
                joinColumns=@JoinColumn(name="simple_item_id"),
                inverseJoinColumns=@JoinColumn(name="complex_item_id"))
    private List<ComplexItem> complexItems;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name="provider_id")
    private Company provider;

    public SimpleItem() {
    }

    public SimpleItem(String name, float nettoValue, float stock, Unit unit, SimpleItemDetail detail, List<ComplexItem> complexItems, Company provider) {
        this.name = name;
        this.nettoValue = nettoValue;
        this.stock = stock;
        this.unit = unit;
        this.detail = detail;
        this.complexItems = complexItems;
        this.provider = provider;
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

    public float getNettoValue() {
        return nettoValue;
    }

    public void setNettoValue(float nettoValue) {
        this.nettoValue = nettoValue;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public SimpleItemDetail getDetail() {
        return detail;
    }

    public void setDetail(SimpleItemDetail detail) {
        this.detail = detail;
    }

    public List<ComplexItem> getComplexItems() {
        return complexItems;
    }

    public void setComplexItems(List<ComplexItem> complexItems) {
        this.complexItems = complexItems;
    }

    public Company getProvider() {
        return provider;
    }

    public void setProvider(Company provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "SimpleItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nettoValue=" + nettoValue +
                ", stock=" + stock +
                ", unit=" + unit +
                ", detail=" + detail +
                ", complexItems=" + complexItems +
                ", provider=" + provider +
                '}';
    }
}
