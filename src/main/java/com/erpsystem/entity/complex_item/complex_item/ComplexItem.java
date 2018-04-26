package com.erpsystem.entity.complex_item.complex_item;

import com.erpsystem.entity.company.company.Company;
import com.erpsystem.entity.complex_item.detail.ComplexItemDetail;
import com.erpsystem.entity.simple_item.simple_item.SimpleItem;
import com.erpsystem.global.Unit;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="complex_item")
public class ComplexItem {

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

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="detail_id")
    private ComplexItemDetail detail;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {CascadeType.MERGE, CascadeType.DETACH,
                            CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(name = "simple_complex",
                joinColumns = @JoinColumn(name = "complex_item_id"),
                inverseJoinColumns = @JoinColumn(name = "simple_item_id"))
    private List<SimpleItem> simpleItems;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY,
               cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name="provider_id")
    private Company provider;

    public ComplexItem() {
    }

    public ComplexItem(String name, float nettoValue, float stockQty, Unit unit, ComplexItemDetail detail, List<SimpleItem> simpleItems, Company provider) {
        this.name = name;
        this.nettoValue = nettoValue;
        this.stock = stockQty;
        this.unit = unit;
        this.detail = detail;
        this.simpleItems = simpleItems;
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

    public float getStockQty() {
        return stock;
    }

    public void setStockQty(float stockQty) {
        this.stock = stockQty;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public ComplexItemDetail getDetail() {
        return detail;
    }

    public void setDetail(ComplexItemDetail detail) {
        this.detail = detail;
    }

    public List<SimpleItem> getSimpleItems() {
        return simpleItems;
    }

    public void setSimpleItems(List<SimpleItem> simpleItems) {
        this.simpleItems = simpleItems;
    }

    public Company getProvider() {
        return provider;
    }

    public void setProvider(Company provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "ComplexItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nettoValue=" + nettoValue +
                ", stockQty=" + stock +
                ", unit=" + unit +
                ", detail=" + detail +
                ", simpleItems=" + simpleItems +
                ", provider=" + provider +
                '}';
    }
}
