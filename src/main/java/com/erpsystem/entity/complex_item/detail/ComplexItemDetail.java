package com.erpsystem.entity.complex_item.detail;

import javax.persistence.*;

@Entity
@Table(name="complex_item_detail")
public class ComplexItemDetail {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="image_path")
    private String imagePath;

    public ComplexItemDetail() {
    }

    public ComplexItemDetail(String imagePath) {
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
        return "ComplexItemDetail{" +
                "id=" + id +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
