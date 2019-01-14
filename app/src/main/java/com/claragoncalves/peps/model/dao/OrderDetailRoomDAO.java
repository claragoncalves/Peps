package com.claragoncalves.peps.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.claragoncalves.peps.model.pojo.OrderDetail;

import java.util.List;

@Dao
public interface OrderDetailRoomDAO {

    @Insert
    void insertOrderDetails(List<OrderDetail> orderDetails);


}
