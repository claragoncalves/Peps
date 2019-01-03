package com.claragoncalves.peps.view.fragments;


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

        addContacts();

        Button buttonAddContacts = view.findViewById(R.id.fragment_add_contact_button_add);
        buttonAddContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.addContacts(adapter.getSelectedContacts());
            }
        });

        return view;
    }

    private void addContacts() {
        List<Contact> contacts = new ArrayList<>();

        Cursor cP = getActivity().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[] { ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.CONTACT_ID },
                ContactsContract.RawContacts.ACCOUNT_TYPE + "= ?",
                new String[] { "com.whatsapp" },
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        int contactPhoneColumn = cP.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        int contactNameColumn = cP.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int contactIdColumn = cP.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);

        while (cP.moveToNext()) {
            contacts.add(new Contact(cP.getString(contactIdColumn), cP.getString(contactNameColumn), null, cP.getString(contactPhoneColumn), null));
        }

        adapter.setContacts(contacts);
        cP.close();

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
