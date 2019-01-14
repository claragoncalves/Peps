package com.claragoncalves.peps.view.activities;


import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Contact;
import com.claragoncalves.peps.model.pojo.OrderDetail;
import com.claragoncalves.peps.view.fragments.AddContactFragment;
import com.claragoncalves.peps.view.fragments.AddOrderFragment;
import com.claragoncalves.peps.view.fragments.ContactDetailFragment;
import com.claragoncalves.peps.view.fragments.ContactListFragment;
import com.claragoncalves.peps.view.fragments.OrderListFragment;
import com.claragoncalves.peps.view.fragments.ProductDetailFragment;
import com.claragoncalves.peps.view.fragments.ProductListFragment;
import com.claragoncalves.peps.view.fragments.ProductPricesFragment;
import com.claragoncalves.peps.viewmodel.ContactViewModel;
import com.claragoncalves.peps.viewmodel.OrderDetailViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductPricesFragment.GoToAddProductListener,
        ProductPricesFragment.GoToProductDetailListener,
        ContactListFragment.GoToAddContactsListener,
        AddContactFragment.AddContactsListener,
        ContactListFragment.GoToContactDetailListener,
        ContactDetailFragment.NewOrderListener,
        AddOrderFragment.CreateNewOrderListener, ProductListFragment.AddOrderDetailsListener {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    private BottomNavigationView bottomNavigationView;
    private ProductPricesFragment productPricesFragment = new ProductPricesFragment();
    private OrderListFragment orderListFragment = new OrderListFragment();
    private ContactListFragment contactListFragment = new ContactListFragment();

    private static final String FRAGMENT_PRODUCT_PRICES_TAG = "fragmentProductPrices";
    private static final String FRAGMENT_PRODUCT_LIST_TAG = "fragmentProductList";
    private static final String FRAGMENT_ORDER_LIST_TAG = "fragmentOrderList";
    private static final String FRAGMENT_CONTACT_LIST_TAG = "fragmentContactList";
    private static final String FRAGMENT_CONTACT_DETAIL_TAG = "fragmentContactDetail";
    private static final String FRAGMENT_ADD_CONTACT_TAG = "fragmentAddContact";
    private static final String FRAGMENT_ADD_ORDER_TAG = "fragmentAddOrder";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placeFragment(contactListFragment, FRAGMENT_CONTACT_LIST_TAG, false);
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
            case R.id.bottom_nav_button_orders:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                placeFragment(orderListFragment, FRAGMENT_ORDER_LIST_TAG, false);
                break;
            case R.id.bottom_nav_button_price:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                placeFragment(productPricesFragment, FRAGMENT_PRODUCT_PRICES_TAG, false);
                break;
            case R.id.bottom_nav_button_contacts:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                placeFragment(contactListFragment, FRAGMENT_CONTACT_LIST_TAG, false);
                break;
        }
    }

    private void placeFragment(Fragment fragment, String tag, Boolean addToBackStack){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_main, fragment, tag);
        if (addToBackStack){
            transaction.addToBackStack(null);
        }
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

    @Override
    public void goToAddContacts() {
        if (checkPermission()){
            requestPermission();
        } else {
            placeFragment(new AddContactFragment(), FRAGMENT_ADD_CONTACT_TAG, true);
        }
    }

    private void requestPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_CONTACTS)) {

            // Show an expanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.

        } else {

            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    }


    private Boolean checkPermission(){
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    placeFragment(new AddContactFragment(), FRAGMENT_ADD_CONTACT_TAG, true);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void addContacts(List<Contact> contacts) {
        ViewModelProviders.of(this).get(ContactViewModel.class).insertContacts(contacts);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goToContactDetail(String contactId) {
        ContactDetailFragment contactDetailFragment = new ContactDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ContactDetailFragment.KEY_CONTACT_ID, contactId);
        contactDetailFragment.setArguments(bundle);
        placeFragment(contactDetailFragment, FRAGMENT_CONTACT_DETAIL_TAG, true);
    }

    @Override
    public void addNewOrder(String contactId) {
        Bundle bundle = new Bundle();
        bundle.putString(ProductListFragment.CONTACT_ID_KEY, contactId);
        AddOrderFragment addOrderFragment = new AddOrderFragment();
        addOrderFragment.setArguments(bundle);
        placeFragment(addOrderFragment, FRAGMENT_ADD_ORDER_TAG, true);
    }

    @Override
    public void createNewOrder(Bundle bundle) {
        ProductListFragment productListFragment = new ProductListFragment();
        productListFragment.setArguments(bundle);
        placeFragment(productListFragment, FRAGMENT_PRODUCT_LIST_TAG, true);
    }

    @Override
    public void addOrderDetails(List<OrderDetail> orderDetails) {
        ViewModelProviders.of(this).get(OrderDetailViewModel.class).insertOrderDetails(orderDetails);
        clearStack();
    }

    public void clearStack() {
        int backStackEntry = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntry > 0) {
            for (int i = 0; i < backStackEntry; i++) {
                getSupportFragmentManager().popBackStackImmediate();
            }
        }
    }


        //    private void showHideBottomNavigation(){
//        if (bottomNavigationView.getVisibility() == View.VISIBLE){
//            bottomNavigationView.setVisibility(View.GONE);
//        } else {
//            bottomNavigationView.setVisibility(View.VISIBLE);
//        }
//    }

}
