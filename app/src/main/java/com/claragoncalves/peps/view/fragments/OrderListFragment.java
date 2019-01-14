
package com.claragoncalves.peps.view.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Order;
import com.claragoncalves.peps.view.adapters.AdapterRecyclerViewOrders;
import com.claragoncalves.peps.viewmodel.OrderViewModel;

import java.util.List;

public class OrderListFragment extends Fragment {

    private AdapterRecyclerViewOrders adapterRecyclerViewOrders;
    private OrderViewModel orderViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);

        RecyclerView recyclerViewOrders = view.findViewById(R.id.fragment_order_list_recyclerview);
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapterRecyclerViewOrders = new AdapterRecyclerViewOrders();
        recyclerViewOrders.setAdapter(adapterRecyclerViewOrders);

        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        orderViewModel.getAllOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(@Nullable List<Order> orders) {
                adapterRecyclerViewOrders.setOrders(orders);
            }
        });

        return view;
    }

}
