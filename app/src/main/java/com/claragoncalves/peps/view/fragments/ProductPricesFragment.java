package com.claragoncalves.peps.view.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Product;
import com.claragoncalves.peps.view.adapters.AdapterRecyclerViewProducts;
import com.claragoncalves.peps.viewmodel.ProductViewModel;

import java.util.List;


public class ProductPricesFragment extends Fragment implements AdapterRecyclerViewProducts.ProductSelectedListener {

    private ProductViewModel productViewModel;
    private AdapterRecyclerViewProducts adapterRecyclerViewProducts;
    private GoToAddProductListener addProductListener;
    private GoToProductDetailListener productDetailListener;
    private RecyclerView recyclerView;
    private FloatingActionButton buttonAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        recyclerView = view.findViewById(R.id.products_fragment_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapterRecyclerViewProducts = new AdapterRecyclerViewProducts(this);
        recyclerView.setAdapter(adapterRecyclerViewProducts);


        buttonAdd = view.findViewById(R.id.products_fragment_button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductListener.goToAddProduct();
            }
        });

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                adapterRecyclerViewProducts.setProducts(products);
            }
        });

        fabShowHide();


        return view;
    }

    private void fabShowHide(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx,int dy){
                super.onScrolled(recyclerView, dx, dy);

                if (dy >0) {
                    if (buttonAdd.isShown()) {
                        buttonAdd.hide();
                    }
                }
                else if (dy <0) {
                    if (!buttonAdd.isShown()) {
                        buttonAdd.show();
                    }
                }
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addProductListener = (GoToAddProductListener) context;
        productDetailListener = (GoToProductDetailListener) context;

    }

    @Override
    public void productSelected(Integer productId) {
        productDetailListener.goToProductDetail(productId);
    }

    public interface GoToAddProductListener{
        void goToAddProduct();
    }

    public interface GoToProductDetailListener{
        void goToProductDetail(Integer id);
    }
}
