package com.erpsystem.entity.simple_item.detail;

import javax.persistence.*;

@Entity
@Table(name="simple_item_detail")
public class SimpleItemDetail {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="image_path")
    private String imagePath;

    @Transient
    private long balance;

    public SimpleItemDetail() {
    }

    public SimpleItemDetail(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "SimpleItemDetail{" +
                "id=" + id +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
