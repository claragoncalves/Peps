package com.claragoncalves.peps.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Contact;
import com.claragoncalves.peps.viewmodel.ContactViewModel;


public class ContactDetailFragment extends Fragment {

    public static final String KEY_CONTACT_ID = "contactId";
    private Contact contact;
    private NewOrderListener newOrderListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_detail, container, false);

        TextView textViewPhone = view.findViewById(R.id.fragment_contact_detail_phone);
        TextView textViewAddress = view.findViewById(R.id.fragment_contact_detail_address);
        TextView textViewDescription = view.findViewById(R.id.fragment_contact_detail_description);

        Bundle bundle = getArguments();
        contact = ViewModelProviders.of(this).get(ContactViewModel.class).getContactById(bundle.getString(KEY_CONTACT_ID));

        CollapsingToolbarLayout collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(contact.getName());

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.collapsingToolbarLayoutExpandedTitleColor);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsingToolbarLayoutCollapsedTitleColor);


        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        textViewPhone.setText(contact.getPhoneNumber());
        textViewAddress.setText("Artigas 634");
        textViewDescription.setText("Martes y jueves el horario de entrega por la tarde.");

        FloatingActionButton buttonOpenWhatsApp = view.findViewById(R.id.fragment_contact_detail_button_whatsapp);
        buttonOpenWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsApp("");
            }
        });

        ImageButton buttonAddNewOrder = view.findViewById(R.id.fragment_contact_detail_button_add_order);
        buttonAddNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newOrderListener.addNewOrder(contact.getId());
            }
        });





        return view;
    }

    public void openWhatsApp(String message){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+ contact.getPhoneNumber() +"&text="+ message));
            startActivity(intent);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        newOrderListener = (NewOrderListener) context;
    }

    public interface NewOrderListener{
        public void addNewOrder(String contactId);
    }

}
