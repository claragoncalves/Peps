package com.claragoncalves.peps.model.repository;

import android.app.Application;
import com.claragoncalves.peps.model.PepsRoomDB;
import com.claragoncalves.peps.model.dao.OrderDetailRoomDAO;
import com.claragoncalves.peps.model.dao.OrderRoomDAO;
import com.claragoncalves.peps.model.pojo.Order;
import com.claragoncalves.peps.model.pojo.OrderContainer;
import com.claragoncalves.peps.model.pojo.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailRepository {

    private OrderDetailRoomDAO orderDetailRoomDAO;
    private OrderRoomDAO orderRoomDAO;

    public OrderDetailRepository(Application application){
        PepsRoomDB pepsRoomDB = PepsRoomDB.getDatabase(application);
        orderDetailRoomDAO = pepsRoomDB.orderDetailDAO();
        orderRoomDAO = pepsRoomDB.orderDAO();
    }

    public List<Integer> getAllIdsOfOrdersFromContact(String contactId){
        return orderDetailRoomDAO.getAllIdsOfOrdersFromContact(contactId);
    }

    public List<OrderContainer> getAllOrderAndDetailsFromContact(String contactId){
        List<OrderContainer> orderContainer = new ArrayList<>();
        for (Integer orderId:getAllIdsOfOrdersFromContact(contactId)) {
            Order order = orderRoomDAO.getOrderById(orderId);
            orderContainer.add(new OrderContainer(order, getContactOrderDetailsFromOrder(contactId, orderId)));
        }
        return orderContainer;
    }

    public void insertOrderDetails(List<OrderDetail> orderDetails){
        orderDetailRoomDAO.insertOrderDetails(orderDetails);
    }

    public List<OrderDetail> getAllOrderDetailsFromOrder(Integer orderId){
        return orderDetailRoomDAO.getAllOrderDetailsFromOrder(orderId);
    }


    public List<OrderDetail> getContactOrderDetailsFromOrder(String contactId, Integer orderId){
        return orderDetailRoomDAO.getContactOrderDetailsFromOrder(contactId, orderId);
    }

}
