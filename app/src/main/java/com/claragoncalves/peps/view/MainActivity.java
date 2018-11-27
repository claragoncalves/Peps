package com.claragoncalves.peps.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.Toast;

import com.claragoncalves.peps.R;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ProductListFragment productListFragment = new ProductListFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeFragment(productListFragment);
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
                placeFragment(productListFragment);
                break;
            case R.id.bottom_nav_button_orders:
                Toast.makeText(this, "PEDIDOS", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bottom_nav_button_price:
                Toast.makeText(this, "PRECIOS", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void placeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container_main, fragment);
        transaction.commit();
    }

    
}
