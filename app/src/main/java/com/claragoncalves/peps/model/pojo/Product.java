package com.claragoncalves.peps.model.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "products_table", indices = {@Index("id")})
public class Product {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;
    private String name;
    private String description;
    private Double buyPrice;
    private Double sellPrice;
    private Integer quantity;

    public Product(Integer id, String name, String description, Double buyPrice, Double sellPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.quantity = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }
}
