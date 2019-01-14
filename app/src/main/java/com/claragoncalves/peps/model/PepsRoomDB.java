package com.claragoncalves.peps.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.claragoncalves.peps.model.dao.ContactRoomDAO;
import com.claragoncalves.peps.model.dao.OrderDetailRoomDAO;
import com.claragoncalves.peps.model.dao.OrderRoomDAO;
import com.claragoncalves.peps.model.dao.ProductRoomDAO;
import com.claragoncalves.peps.model.pojo.Contact;
import com.claragoncalves.peps.model.pojo.Order;
import com.claragoncalves.peps.model.pojo.OrderDetail;
import com.claragoncalves.peps.model.pojo.Product;

@Database(entities = {Product.class, Contact.class, Order.class, OrderDetail.class}, version = 1,exportSchema = false)
public abstract class PepsRoomDB extends RoomDatabase {
    private static final String PEPS_ROOM_DB = "peps_room_db";

    public abstract ProductRoomDAO productDAO();
    public abstract ContactRoomDAO contactDAO();
    public abstract OrderRoomDAO orderDAO();
    public abstract OrderDetailRoomDAO orderDetailDAO();

    private static volatile PepsRoomDB INSTANCE;

    public static PepsRoomDB getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (PepsRoomDB.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PepsRoomDB.class, PEPS_ROOM_DB).allowMainThreadQueries().build();
                }
            }
        }

        return INSTANCE;
    }

}
