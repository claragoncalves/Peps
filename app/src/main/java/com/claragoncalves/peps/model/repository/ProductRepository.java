package com.claragoncalves.peps.model.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.claragoncalves.peps.model.PepsRoomDB;
import com.claragoncalves.peps.model.dao.ProductRoomDAO;
import com.claragoncalves.peps.model.pojo.Product;

import java.util.List;

public class ProductRepository {
    private ProductRoomDAO productDAO;
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

    public Product getProductById(Integer id){
        return productDAO.getProductById(id);
    }

    public void modifyProduct(Product product){
        productDAO.modifyProduct(product);
    }

    public void deleteProduct(Product product){
        productDAO.deleteProduct(product);
    }
}
