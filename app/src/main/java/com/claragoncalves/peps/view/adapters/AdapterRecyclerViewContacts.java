package com.claragoncalves.peps.view.adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Contact;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerViewContacts extends RecyclerView.Adapter<AdapterRecyclerViewContacts.ContactViewHolder> {

    private List<Contact> contacts;
    private ContactDetailListener listener;

    public AdapterRecyclerViewContacts(ContactDetailListener listener) {
        contacts = new ArrayList<>();
        this.listener = listener;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ContactViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_contact_recycler, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {
        contactViewHolder.bindContact(contacts.get(i));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewContactName;
        private TextView textViewContactInitial;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewContactName = itemView.findViewById(R.id.cell_contact_recycler_textview_name);
            textViewContactInitial = itemView.findViewById(R.id.cell_contact_recycler_textview_initial);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.viewContact(contacts.get(getAdapterPosition()).getId());
                }
            });
        }

        public void bindContact(Contact contact){
            textViewContactName.setText(contact.getName());
            String letter = String.valueOf(contact.getName().charAt(0));
            textViewContactInitial.setText(letter);
        }
    }


    public interface ContactDetailListener{
        public void viewContact(String contactId);
    }

}
