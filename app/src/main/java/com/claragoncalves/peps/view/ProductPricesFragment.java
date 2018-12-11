package com.claragoncalves.peps.view;


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

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Product;
import com.claragoncalves.peps.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;


public class ProductPricesFragment extends Fragment {

    private ProductViewModel productViewModel;
    private AdapterRecyclerViewProducts adapterRecyclerViewProducts;
    private GoToAddProductListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.products_fragment_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapterRecyclerViewProducts = new AdapterRecyclerViewProducts();
        recyclerView.setAdapter(adapterRecyclerViewProducts);


        Button buttonAdd = view.findViewById(R.id.products_fragment_button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.goToAddProduct();
            }
        });

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                adapterRecyclerViewProducts.setProducts(products);
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (GoToAddProductListener) context;
    }

    public interface GoToAddProductListener{
        void goToAddProduct();
    }

}
