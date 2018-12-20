package com.claragoncalves.peps.view.activities;

import android.app.ActivityOptions;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Product;
import com.claragoncalves.peps.view.fragments.AddProductFragment;
import com.claragoncalves.peps.view.fragments.OrderListFragment;
import com.claragoncalves.peps.view.fragments.ProductDetailFragment;
import com.claragoncalves.peps.view.fragments.ProductListFragment;
import com.claragoncalves.peps.view.fragments.ProductPricesFragment;
import com.claragoncalves.peps.viewmodel.ProductViewModel;

public class MainActivity extends AppCompatActivity implements ProductPricesFragment.GoToAddProductListener, ProductPricesFragment.GoToProductDetailListener {

    private BottomNavigationView bottomNavigationView;
    private ProductListFragment productListFragment = new ProductListFragment();
    private ProductPricesFragment productPricesFragment = new ProductPricesFragment();
    private OrderListFragment orderListFragment = new OrderListFragment();
    private static final String FRAGMENT_PRODUCT_PRICES_TAG = "fragmentProductPrices";
    private static final String FRAGMENT_PRODUCT_LIST_TAG = "fragmentProductList";
    private static final String FRAGMENT_ORDER_LIST_TAG = "fragmentOrderList";


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
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                placeFragment(productListFragment, FRAGMENT_PRODUCT_LIST_TAG);
                break;
            case R.id.bottom_nav_button_orders:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                placeFragment(orderListFragment, FRAGMENT_ORDER_LIST_TAG);
                break;
            case R.id.bottom_nav_button_price:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                placeFragment(productPricesFragment, FRAGMENT_PRODUCT_PRICES_TAG);
                break;
        }
    }

    private void placeFragment(Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_main, fragment, tag);
        transaction.commit();
    }

    @Override
    public void goToAddProduct() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToProductDetail(Integer id) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(ProductDetailFragment.PRODUCT_ID_KEY, id);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    //    private void showHideBottomNavigation(){
//        if (bottomNavigationView.getVisibility() == View.VISIBLE){
//            bottomNavigationView.setVisibility(View.GONE);
//        } else {
//            bottomNavigationView.setVisibility(View.VISIBLE);
//        }
//    }

}
