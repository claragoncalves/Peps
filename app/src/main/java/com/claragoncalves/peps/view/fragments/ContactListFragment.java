package com.claragoncalves.peps.view.fragments;


import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Contact;
import com.claragoncalves.peps.view.adapters.AdapterRecyclerViewContacts;
import com.claragoncalves.peps.viewmodel.ContactViewModel;
import java.util.List;


public class ContactListFragment extends Fragment implements AdapterRecyclerViewContacts.ContactDetailListener {

    private ContactViewModel contactViewModel;
    private AdapterRecyclerViewContacts adapterRecyclerViewContacts;
    private GoToAddContactsListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        adapterRecyclerViewContacts = new AdapterRecyclerViewContacts(this);
        RecyclerView recyclerView = view.findViewById(R.id.fragment_contact_list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapterRecyclerViewContacts);

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        contactViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable List<Contact> contacts) {
                adapterRecyclerViewContacts.setContacts(contacts);
            }
        });

        FloatingActionButton addButton = view.findViewById(R.id.fragment_contact_list_add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.goToAddContacts();

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (GoToAddContactsListener) context;
    }

    @Override
    public void viewContact(String contactId) {
        openWhatsApp();
    }


    public void openWhatsApp(){
        try {
            String text = "This is a test";
            String toNumber = "5491167855605";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
            startActivity(intent);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public interface GoToAddContactsListener{
        public void goToAddContacts();
    }
}
