package com.claragoncalves.peps.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.claragoncalves.peps.model.dao.ContactDAO;
import com.claragoncalves.peps.model.dao.OrderDAO;
import com.claragoncalves.peps.model.dao.ProductDAO;
import com.claragoncalves.peps.model.pojo.Contact;
import com.claragoncalves.peps.model.pojo.Order;
import com.claragoncalves.peps.model.pojo.OrderDetail;
import com.claragoncalves.peps.model.pojo.Product;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {Product.class, Contact.class, Order.class, OrderDetail.class}, version = 1,exportSchema = false)
public abstract class PepsRoomDB extends RoomDatabase {
    private static final String PEPS_ROOM_DB = "peps_room_db";

    public abstract ProductDAO productDAO();
    public abstract ContactDAO contactDAO();
    public abstract OrderDAO orderDAO();

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
