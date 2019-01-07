package com.claragoncalves.peps.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.claragoncalves.peps.model.pojo.Product;

import java.util.List;

@Dao
public interface ProductRoomDAO {

    @Query("SELECT * from products_table ORDER BY name ASC")
    LiveData<List<Product>> getAllProducts();

    @Insert
    void insertProduct(Product product);

    @Delete
    void deleteProduct(Product product);

    @Update
    void modifyProduct(Product product);

    @Query("DELETE FROM products_table")
    void deleteAll();

    @Query("SELECT * FROM products_table WHERE id == :id")
    Product getProductById(Integer id);
}
