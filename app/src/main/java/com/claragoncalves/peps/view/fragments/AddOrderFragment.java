package com.claragoncalves.peps.view.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Order;
import com.claragoncalves.peps.viewmodel.OrderViewModel;

import java.util.Date;


public class AddOrderFragment extends Fragment {
    private EditText editTextOrderName;
    private EditText editTextOrderDescription;
    private CreateNewOrderListener listener;
    private OrderViewModel orderViewModel;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_order, container, false);

        bundle = getArguments();

        editTextOrderName = view.findViewById(R.id.fragment_add_order_name);
        editTextOrderDescription = view.findViewById(R.id.fragment_add_order_description);

        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);

        Button buttonAddOrder = view.findViewById(R.id.fragment_add_order_button_add);
        buttonAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer orderId = (int) (long) orderViewModel.insertOrder(bindOrder());
                bundle.putInt(ProductListFragment.ORDER_ID_KEY, orderId);
                listener.createNewOrder(bundle);
            }
        });


        final Spinner spinnerExistingOrders = view.findViewById(R.id.fragment_add_order_spinner_existing);
        spinnerExistingOrders.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, orderViewModel.getAllOrdersNames()));

        Button buttonAddToExistingOrder = view.findViewById(R.id.fragment_add_order_button_add_to_existing);
        buttonAddToExistingOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer orderId = orderViewModel.findOrderIdFromName(spinnerExistingOrders.getSelectedItem().toString());
                bundle.putInt(ProductListFragment.ORDER_ID_KEY, orderId);
                listener.createNewOrder(bundle);
            }
        });

        return view;
    }

    public Order bindOrder(){
        String orderName = editTextOrderName.getText().toString();
        String orderDescription = editTextOrderDescription.getText().toString();
        return new Order(orderName, orderDescription);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (CreateNewOrderListener) context;
    }

    public interface CreateNewOrderListener{
        public void createNewOrder(Bundle bundle);
    }

}
