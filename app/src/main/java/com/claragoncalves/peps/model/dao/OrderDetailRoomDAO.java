package com.claragoncalves.peps.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.claragoncalves.peps.model.pojo.OrderDetail;

import java.util.List;

@Dao
public interface OrderDetailRoomDAO {

    @Insert
    void insertOrderDetails(List<OrderDetail> orderDetails);

    @Query("SELECT *, SUM(productQuantity) FROM order_detail_table WHERE orderId == :orderId GROUP BY orderId")
    List<OrderDetail> getAllOrderDetailsFromOrder(Integer orderId);

    @Query("SELECT orderId FROM order_detail_table WHERE contactId == :contactId GROUP BY orderId")
    List<Integer> getAllIdsOfOrdersFromContact(String contactId);

    @Query("SELECT * FROM order_detail_table WHERE orderId == :orderId AND contactId == :contactId")
    List<OrderDetail> getContactOrderDetailsFromOrder(String contactId, Integer orderId);
}
