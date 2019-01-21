package com.claragoncalves.peps.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.claragoncalves.peps.model.pojo.Order;
import com.claragoncalves.peps.model.repository.OrderRepository;

import java.util.List;

public class OrderViewModel extends AndroidViewModel {

    private OrderRepository repository;
    private LiveData<List<Order>> orders;


    public OrderViewModel(@NonNull Application application) {
        super(application);
        repository = new OrderRepository(application);
        orders = repository.getAllOrders();
    }

    public LiveData<List<Order>> getAllOrders() {
        return orders;
    }

    public Long insertOrder(Order order){
        return repository.insertOrder(order);
    }

    public Order getOrderById(Integer orderId){
        return repository.getOrderById(orderId);
    }

    public List<String> getAllOrdersNames(){
        return repository.getAllOrdersNames();
    }

    public Integer findOrderIdFromName(String orderName){
        return repository.findOrderIdFromName(orderName);
    }
}
