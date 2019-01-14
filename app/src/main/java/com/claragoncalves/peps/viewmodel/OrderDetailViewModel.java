package com.claragoncalves.peps.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.claragoncalves.peps.model.pojo.OrderDetail;
import com.claragoncalves.peps.model.repository.OrderDetailRepository;

import java.util.List;

public class OrderDetailViewModel extends AndroidViewModel {

    private OrderDetailRepository repository;

    public OrderDetailViewModel(@NonNull Application application) {
        super(application);
        repository = new OrderDetailRepository(application);
    }

    public void insertOrderDetails(List<OrderDetail> orderDetails){
        repository.insertOrderDetails(orderDetails);
    }

}
