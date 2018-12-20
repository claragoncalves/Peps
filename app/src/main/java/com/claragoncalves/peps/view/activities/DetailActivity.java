package com.claragoncalves.peps.view.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Product;
import com.claragoncalves.peps.view.fragments.ProductDetailFragment;
import com.claragoncalves.peps.viewmodel.ProductViewModel;

public class DetailActivity extends AppCompatActivity implements ProductDetailFragment.ProductDetailListener{

    private static final String FRAGMENT_PRODUCT_DETAIL_TAG = "fragmentProductDetail";
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        productDetailFragment.setArguments(bundle);
        placeFragment(productDetailFragment, FRAGMENT_PRODUCT_DETAIL_TAG);
    }


    private void placeFragment(Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_detail, fragment, tag);
        transaction.commit();
    }

    @Override
    public void updateProduct(Product product) {
        productViewModel.modifyProduct(product);
        finish();

    }

    @Override
    public void deleteProduct(Product product) {
        productViewModel.deleteProduct(product);
        finish();
    }
}
