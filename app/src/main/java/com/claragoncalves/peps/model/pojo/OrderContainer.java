package com.claragoncalves.peps.model.pojo;

import java.util.Date;
import java.util.List;

public class OrderContainer {
    private Order order;
    private List<OrderDetail> orderDetails;

    public OrderContainer(Order order, List<OrderDetail> orderDetails) {
        this.order = order;
        this.orderDetails = orderDetails;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public Order getOrder() {
        return order;
    }
}
