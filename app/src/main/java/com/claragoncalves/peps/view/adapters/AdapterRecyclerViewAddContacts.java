package com.claragoncalves.peps.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Contact;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerViewAddContacts extends RecyclerView.Adapter<AdapterRecyclerViewAddContacts.AddContactViewHolder> {

    private List<Contact> contacts;

    public AdapterRecyclerViewAddContacts() {
        contacts = new ArrayList<>();
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }


    public List<Contact> getSelectedContacts(){
        List<Contact> selectedContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getAddToDb()){
                selectedContacts.add(contact);
            }
        }
        return selectedContacts;
    }

    @NonNull
    @Override
    public AddContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AddContactViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_add_contact_recycler, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddContactViewHolder contactViewHolder, int i) {
        contactViewHolder.bindContact(contacts.get(i));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class AddContactViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewContactName;
        private CheckBox checkBoxContactToAdd;

        public AddContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewContactName = itemView.findViewById(R.id.cell_add_contact_recycler_textview_name);
            checkBoxContactToAdd = itemView.findViewById(R.id.cell_add_contact_recycler_checkbox_add);
            checkBoxContactToAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBoxContactToAdd.isChecked()){
                        contacts.get(getAdapterPosition()).setAddToDb(!contacts.get(getAdapterPosition()).getAddToDb());
                    }
                }
            });
        }

        public void bindContact(Contact contact){
            textViewContactName.setText(contact.getName());
            checkBoxContactToAdd.setChecked(contact.getAddToDb());
        }
    }
}
