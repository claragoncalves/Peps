package com.claragoncalves.peps.view.activities;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Contact;
import com.claragoncalves.peps.view.fragments.AddContactFragment;
import com.claragoncalves.peps.view.fragments.ContactDetailFragment;
import com.claragoncalves.peps.view.fragments.ContactListFragment;
import com.claragoncalves.peps.viewmodel.ContactViewModel;

import java.util.List;

public class ContactActivity extends AppCompatActivity implements ContactListFragment.GoToAddContactsListener, AddContactFragment.AddContactsListener, ContactListFragment.GoToContactDetailListener {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static final String FRAGMENT_CONTACT_LIST_TAG = "fragmentContactList";
    private static final String FRAGMENT_CONTACT_DETAIL_TAG = "fragmentContactDetail";
    private static final String FRAGMENT_ADD_CONTACT_TAG = "fragmentAddContact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        placeFragment(new ContactListFragment(), FRAGMENT_CONTACT_LIST_TAG);
    }

    private void placeFragment(Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_contact_fragment_container, fragment, tag);
        if (!tag.equals(FRAGMENT_CONTACT_LIST_TAG)) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void goToAddContacts() {
        if (checkPermission()){
            requestPermission();
        } else {
            placeFragment(new AddContactFragment(), FRAGMENT_ADD_CONTACT_TAG);
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
                    placeFragment(new AddContactFragment(), FRAGMENT_ADD_CONTACT_TAG);
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
        getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public void goToContactDetail(String contactId) {
        ContactDetailFragment contactDetailFragment = new ContactDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ContactDetailFragment.KEY_CONTACT_ID, contactId);
        contactDetailFragment.setArguments(bundle);
        placeFragment(contactDetailFragment, FRAGMENT_CONTACT_DETAIL_TAG);
    }
}
