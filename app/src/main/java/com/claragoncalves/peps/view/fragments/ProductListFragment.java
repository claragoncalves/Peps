package com.claragoncalves.peps.view.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.OrderDetail;
import com.claragoncalves.peps.model.pojo.Product;
import com.claragoncalves.peps.view.adapters.AdapterRecyclerProducts;
import com.claragoncalves.peps.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;


public class ProductListFragment extends Fragment implements AdapterRecyclerProducts.SumInterface {

    public static final String ORDER_ID_KEY = "orderId";
    public static final String CONTACT_ID_KEY = "contactId";

    private ProductViewModel productViewModel;
    private AdapterRecyclerProducts adapterRecyclerProducts;
    private TextView textViewTotal;
    private String contactId;
    private Integer orderId;
    private AddOrderDetailsListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        Bundle bundle = getArguments();

        contactId = bundle.getString(CONTACT_ID_KEY);
        orderId = bundle.getInt(ORDER_ID_KEY);

        textViewTotal = view.findViewById(R.id.fragment_product_list_textview_total);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_product_list_recyclerview);
        adapterRecyclerProducts = new AdapterRecyclerProducts(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapterRecyclerProducts);

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                adapterRecyclerProducts.setProducts(products);
            }
        });


        Button buttonAddOrderDetails = view.findViewById(R.id.fragment_product_list_button_add_products);
        buttonAddOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<OrderDetail> orderDetails = new ArrayList<>();
                for (Product product : adapterRecyclerProducts.getProducts()) {
                    if (product.getQuantity() > 0){
                        orderDetails.add(new OrderDetail(orderId, contactId, product.getId(), product.getQuantity()));
                    }
                }
                listener.addOrderDetails(orderDetails);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (AddOrderDetailsListener) context;
    }

    @Override
    public void quantityTapped(String total) {
        textViewTotal.setText(total);
    }

    public interface AddOrderDetailsListener{
        public void addOrderDetails(List<OrderDetail> orderDetails);
    }

}
