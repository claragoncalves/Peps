package com.claragoncalves.peps.view;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.Toast;

import com.claragoncalves.peps.R;

public class MainActivity extends AppCompatActivity implements ProductPricesFragment.GoToAddProductListener {
    private BottomNavigationView bottomNavigationView;
    private ProductListFragment productListFragment = new ProductListFragment();
    private ProductPricesFragment productPricesFragment = new ProductPricesFragment();
    private static final String FRAGMENT_ADD_PRODUCT_TAG = "fragmentAddProduct";
    private static final String FRAGMENT_PRODUCT_PRICES_TAG = "fragmentProductPrices";
    private static final String FRAGMENT_PRODUCT_LIST_TAG = "fragmentProductList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeFragment(productListFragment, FRAGMENT_PRODUCT_LIST_TAG);
        setBottomNavigationView();

    }

    public void setBottomNavigationView(){
        bottomNavigationView = findViewById(R.id.bottom_navigation_main);
        bottomNavigationView.setItemIconTintList(null);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        bottomNavigationView.setItemIconSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 28, displayMetrics));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                bottomNavigationItemSelected(menuItem);
                return true;
            }
        });
    }

    public void bottomNavigationItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.bottom_nav_button_buy:
                placeFragment(productListFragment, FRAGMENT_PRODUCT_LIST_TAG);
                break;
            case R.id.bottom_nav_button_orders:
                Toast.makeText(this, "PEDIDOS", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bottom_nav_button_price:
                placeFragment(productPricesFragment, FRAGMENT_PRODUCT_PRICES_TAG);
                break;
        }
    }

    private void placeFragment(Fragment fragment, String tag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container_main, fragment, tag);
        if (tag.equals(FRAGMENT_ADD_PRODUCT_TAG)){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }


    @Override
    public void goToAddProduct() {
        placeFragment(new AddProductFragment(), FRAGMENT_ADD_PRODUCT_TAG);
    }
}
