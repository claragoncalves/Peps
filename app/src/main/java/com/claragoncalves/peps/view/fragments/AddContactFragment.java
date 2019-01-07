package com.claragoncalves.peps.view.fragments;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Contact;
import com.claragoncalves.peps.view.adapters.AdapterRecyclerViewAddContacts;
import com.claragoncalves.peps.viewmodel.ContactViewModel;
import com.claragoncalves.peps.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;


public class AddContactFragment extends Fragment {
    private AdapterRecyclerViewAddContacts adapter;
    private AddContactsListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_add_contact_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new AdapterRecyclerViewAddContacts();
        recyclerView.setAdapter(adapter);

        Button buttonAddContacts = view.findViewById(R.id.fragment_add_contact_button_add);
        buttonAddContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.addContacts(adapter.getSelectedContacts());
            }
        });

        
        adapter.setContacts(ViewModelProviders.of(this).get(ContactViewModel.class).getUserContacts(getContext()));

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (AddContactsListener) context;
    }

    public interface AddContactsListener{
        public void addContacts(List<Contact> contacts);
    }

}
