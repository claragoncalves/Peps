package com.claragoncalves.peps.model.repository;

import android.app.Application;

import com.claragoncalves.peps.model.PepsRoomDB;
import com.claragoncalves.peps.model.dao.OrderDetailRoomDAO;
import com.claragoncalves.peps.model.pojo.OrderDetail;

import java.util.List;

public class OrderDetailRepository {

    private OrderDetailRoomDAO orderDetailRoomDAO;

    public OrderDetailRepository(Application application){
        PepsRoomDB pepsRoomDB = PepsRoomDB.getDatabase(application);
        orderDetailRoomDAO = pepsRoomDB.orderDetailDAO();
    }

    public void insertOrderDetails(List<OrderDetail> orderDetails){
        orderDetailRoomDAO.insertOrderDetails(orderDetails);
    }

}
