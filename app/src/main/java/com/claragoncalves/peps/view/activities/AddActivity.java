package com.claragoncalves.peps.view.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Product;
import com.claragoncalves.peps.view.fragments.AddProductFragment;
import com.claragoncalves.peps.viewmodel.ProductViewModel;

public class AddActivity extends AppCompatActivity implements AddProductFragment.ProductToAddListener{

    private static final String FRAGMENT_ADD_PRODUCT_TAG = "fragmentAddProduct";

    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        placeFragment(new AddProductFragment(), FRAGMENT_ADD_PRODUCT_TAG);

    }


    private void placeFragment(Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_add, fragment, tag);
        transaction.commit();
    }

    @Override
    public void addProduct(Product product) {
        productViewModel.insertProduct(product);
        finish();
    }
}
