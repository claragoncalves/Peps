package com.claragoncalves.peps.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.claragoncalves.peps.model.pojo.Order;

import java.util.List;

@Dao
public interface OrderRoomDAO {

    @Insert
    Long insertOrder(Order order);

    @Query("SELECT * FROM orders_table ORDER BY date ASC")
    LiveData<List<Order>> getAllOrders();

    @Query("SELECT * FROM orders_table WHERE id == :id")
    Order getOrderById(Integer id);

    @Delete
    void deleteOrder(Order order);

    @Query("SELECT name FROM orders_table")
    List<String> getAllOrdersNames();

    @Query("SELECT id FROM orders_table WHERE name == :orderName")
    Integer findOrderIdFromName(String orderName);
}
