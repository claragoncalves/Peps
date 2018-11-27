package com.claragoncalves.peps.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.claragoncalves.peps.model.pojo.Product;
import com.claragoncalves.peps.model.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository repository;
    private LiveData<List<Product>> products;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductRepository(application);
        products = repository.getAllProducts();
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public void insertProduct(Product product){
        repository.insertProduct(product);
    }
}
