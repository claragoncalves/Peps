package com.claragoncalves.peps.view.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Product;
import com.claragoncalves.peps.viewmodel.ProductViewModel;


public class ProductDetailFragment extends Fragment {
    public static final String PRODUCT_ID_KEY = "productId";

    private ProductDetailListener productDetailListener;
    private Product product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        Bundle bundle = getArguments();
        Integer productId = bundle.getInt(PRODUCT_ID_KEY);

        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        product = productViewModel.getProductById(productId);


        EditText editTextProductName = view.findViewById(R.id.fragment_product_detail_name);
        editTextProductName.setBackgroundResource(android.R.color.transparent);
        editTextProductName.setText(product.getName());


        Button buttonUpdateProduct = view.findViewById(R.id.fragment_product_detail_button_update);
        buttonUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updateListener.updateProduct();
            }
        });


        Button buttonDeleteProduct = view.findViewById(R.id.fragment_product_detail_button_delete);
        buttonDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productDetailListener.deleteProduct(product);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        productDetailListener = (ProductDetailListener) context;
    }

    public interface ProductDetailListener{
        void updateProduct(Product product);
        void deleteProduct(Product product);

    }
}
