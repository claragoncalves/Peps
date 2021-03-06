package com.claragoncalves.peps.model.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.claragoncalves.peps.model.PepsRoomDB;
import com.claragoncalves.peps.model.dao.ProductDAO;
import com.claragoncalves.peps.model.pojo.Product;

import java.util.List;

public class ProductRepository {
    private ProductDAO productDAO;
    private LiveData<List<Product>> products;

    public ProductRepository(Application application){
        PepsRoomDB pepsRoomDB = PepsRoomDB.getDatabase(application);
        productDAO = pepsRoomDB.productDAO();
        products = productDAO.getAllProducts();
    }

    public LiveData<List<Product>> getAllProducts(){
        return products;
    }

    public void insertProduct(Product product){
        productDAO.insertProduct(product);
    }

}
