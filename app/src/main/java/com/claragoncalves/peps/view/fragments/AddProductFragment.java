package com.claragoncalves.peps.view.fragments;


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


public class AddProductFragment extends Fragment {
    private EditText editTextName;
    private EditText editTextDescription;
    private EditText editTextBuyPrice;
    private EditText editTextSellPrice;
    private ProductToAddListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);

        editTextName = view.findViewById(R.id.fragment_add_product_edit_text_name);
        editTextDescription = view.findViewById(R.id.fragment_add_product_edit_text_description);
        editTextBuyPrice = view.findViewById(R.id.fragment_add_product_edit_text_buy_price);
        editTextSellPrice = view.findViewById(R.id.fragment_add_product_edit_text_sell_price);


        Button buttonAddProduct = view.findViewById(R.id.fragment_add_product_button_add);
        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.addProduct(bindProduct());
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (ProductToAddListener) context;
    }

    private Product bindProduct(){
        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();
        Double buyPrice = Double.parseDouble(editTextBuyPrice.getText().toString());
        Double sellPrice = Double.parseDouble(editTextSellPrice.getText().toString());
        return new Product(null, name, description, buyPrice, sellPrice);
    }

    public interface ProductToAddListener{
        void addProduct(Product product);
    }

}
