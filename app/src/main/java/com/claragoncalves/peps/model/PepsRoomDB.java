package com.claragoncalves.peps.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.claragoncalves.peps.model.dao.ProductDAO;
import com.claragoncalves.peps.model.pojo.Product;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {Product.class}, version = 1,exportSchema = false)
public abstract class PepsRoomDB extends RoomDatabase {
    private static final String PEPS_ROOM_DB = "peps_room_db";
    public abstract ProductDAO productDAO();
    private static volatile PepsRoomDB INSTANCE;

    public static PepsRoomDB getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (PepsRoomDB.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PepsRoomDB.class, PEPS_ROOM_DB).addCallback(roomDBCallback).build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDBCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ProductDAO dao;

        PopulateDbAsync(PepsRoomDB db) {
            dao = db.productDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            dao.deleteAll();

            List<Product> productos = new ArrayList<>();
            productos.add(new Product(null,"Yerba Kalena", "Pack 12 x 500gr", 360.0,440.0));
            productos.add(new Product(null, "Yerba Kalena", "Pack 5 x 2kg", 570.0,696.0));
            productos.add(new Product(null,"Yerba Kalena", "Granel x 12kg", 504.0, 672.0 ));
            productos.add(new Product(null,"Azucar Balajú", "Bolsa x 10kg", 350.0, 480.0 ));
            productos.add(new Product(null,"Azucar Balajú", "Pack 24 x 500gr", 480.0, 625.0 ));
            productos.add(new Product(null,"Azucar Balajú", "Bolsa x 5kg", 175.0, 240.0 ));
            productos.add(new Product(null,"Té Kalena Boldo", "20 cajas x 24 saquitos", 400.0, 513.0 ));
            productos.add(new Product(null,"Té Kalena Rojo, Verde, Manzanilla", "20 cajas x 24 saquitos", 340.0, 432.0 ));

            for (Product producto:productos) {
                dao.insertProduct(producto);
            }
            return null;
        }
    }
}
