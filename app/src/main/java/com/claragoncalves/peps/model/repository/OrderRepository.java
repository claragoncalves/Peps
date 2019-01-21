package com.claragoncalves.peps.model.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.claragoncalves.peps.model.PepsRoomDB;
import com.claragoncalves.peps.model.dao.OrderRoomDAO;
import com.claragoncalves.peps.model.pojo.Order;

import java.util.List;

public class OrderRepository {
    private OrderRoomDAO orderRoomDAO;
    private LiveData<List<Order>> orders;

    public OrderRepository(Application application){
        PepsRoomDB pepsRoomDB = PepsRoomDB.getDatabase(application);
        orderRoomDAO = pepsRoomDB.orderDAO();
        orders = orderRoomDAO.getAllOrders();
    }

    public LiveData<List<Order>> getAllOrders(){
        return orders;
    }

    public Long insertOrder(Order order){
        return orderRoomDAO.insertOrder(order);
    }

    public Order getOrderById(Integer id){
        return orderRoomDAO.getOrderById(id);
    }

    public void deleteOrder(Order order){
        orderRoomDAO.deleteOrder(order);
    }

    public List<String> getAllOrdersNames(){
        return orderRoomDAO.getAllOrdersNames();
    }

    public Integer findOrderIdFromName(String orderName){
        return orderRoomDAO.findOrderIdFromName(orderName);
    }


}
