package com.claragoncalves.peps.model.pojo;


import java.util.ArrayList;
import java.util.List;

public class OrderContainer {
    private Order order;
    private List<Product> products;

    public OrderContainer(Order order) {
        this.order = order;
        products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Order getOrder() {
        return order;
    }
}
