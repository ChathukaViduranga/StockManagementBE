package com.srmobile.dashboard.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Items {

    @Id
    @Column(name="item_no", length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="item_name", nullable = false, length = 100)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sellingPrice;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cost;

    @Lob
    private String description;

    @Column(name="image_path")
    private String imagePath;

    @Enumerated(EnumType.STRING)
    private ItemStatus status = ItemStatus.AVAILABLE;

    @Enumerated(EnumType.STRING)
    @Column(name="category", nullable = false, length = 10)
    private Category category = Category.O;

    public enum ItemStatus { AVAILABLE, OUT_OF_STOCK, DISCONTINUED }

    public enum Category {
        MP,
        G,
        S,
        A,
        V,
        K,
        P,
        O,
    }
    public Items(){}

    public Items(String name, BigDecimal sellingPrice, BigDecimal cost, String description, String imagePath, Category category) {
        this.name = name;
        this.sellingPrice = sellingPrice;
        this.cost = cost;
        this.description = description;
        this.imagePath = imagePath;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", status=" + status +
                ", category=" + category +
                '}';
    }
}