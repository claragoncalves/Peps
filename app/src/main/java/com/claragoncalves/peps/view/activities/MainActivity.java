package com.claragoncalves.peps.view.activities;

import android.arch.lifecycle.ViewModelProviders;
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
import com.claragoncalves.peps.model.pojo.Product;
import com.claragoncalves.peps.view.fragments.AddProductFragment;
import com.claragoncalves.peps.view.fragments.OrderListFragment;
import com.claragoncalves.peps.view.fragments.ProductDetailFragment;
import com.claragoncalves.peps.view.fragments.ProductListFragment;
import com.claragoncalves.peps.view.fragments.ProductPricesFragment;
import com.claragoncalves.peps.viewmodel.ProductViewModel;

public class MainActivity extends AppCompatActivity implements ProductPricesFragment.GoToAddProductListener, AddProductFragment.ProductToAddListener, ProductPricesFragment.GoToProductDetailListener, ProductDetailFragment.ProductDeleteListener, ProductDetailFragment.ProductUpdateListener {

    private ProductViewModel productViewModel;
    private BottomNavigationView bottomNavigationView;
    private ProductListFragment productListFragment = new ProductListFragment();
    private ProductPricesFragment productPricesFragment = new ProductPricesFragment();
    private OrderListFragment orderListFragment = new OrderListFragment();
    private static final String FRAGMENT_ADD_PRODUCT_TAG = "fragmentAddProduct";
    private static final String FRAGMENT_PRODUCT_DETAIL_TAG = "fragmentProductDetail";
    private static final String FRAGMENT_PRODUCT_PRICES_TAG = "fragmentProductPrices";
    private static final String FRAGMENT_PRODUCT_LIST_TAG = "fragmentProductList";
    private static final String FRAGMENT_ORDER_LIST_TAG = "fragmentOrderList";
    private FragmentManager fragmentManager = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
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
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                placeFragment(productListFragment, FRAGMENT_PRODUCT_LIST_TAG);
                break;
            case R.id.bottom_nav_button_orders:
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                placeFragment(orderListFragment, FRAGMENT_ORDER_LIST_TAG);
                break;
            case R.id.bottom_nav_button_price:
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                placeFragment(productPricesFragment, FRAGMENT_PRODUCT_PRICES_TAG);
                break;
        }
    }

    private void placeFragment(Fragment fragment, String tag){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container_main, fragment, tag);
        if (tag.equals(FRAGMENT_ADD_PRODUCT_TAG) || tag.equals(FRAGMENT_PRODUCT_DETAIL_TAG)){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

//    private void showHideBottomNavigation(){
//        if (bottomNavigationView.getVisibility() == View.VISIBLE){
//            bottomNavigationView.setVisibility(View.GONE);
//        } else {
//            bottomNavigationView.setVisibility(View.VISIBLE);
//        }
//    }

    @Override
    public void goToAddProduct() {
        placeFragment(new AddProductFragment(), FRAGMENT_ADD_PRODUCT_TAG);
    }

    @Override
    public void addProduct(Product product) {
        productViewModel.insertProduct(product);
        fragmentManager.popBackStackImmediate();
    }

    @Override
    public void goToProductDetail(Integer id) {
        Bundle bundle = new Bundle();
        bundle.putInt(ProductDetailFragment.PRODUCT_ID_KEY, id);
        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        productDetailFragment.setArguments(bundle);
        placeFragment(productDetailFragment, FRAGMENT_PRODUCT_DETAIL_TAG);
    }

    @Override
    public void updateProduct(Product product) {
        productViewModel.modifyProduct(product);
        fragmentManager.popBackStackImmediate();

    }

    @Override
    public void deleteProduct(Product product) {
        productViewModel.deleteProduct(product);
        fragmentManager.popBackStackImmediate();
    }
}
