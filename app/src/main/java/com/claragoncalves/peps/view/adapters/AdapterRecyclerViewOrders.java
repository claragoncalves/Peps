package com.claragoncalves.peps.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Order;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerViewOrders extends RecyclerView.Adapter<AdapterRecyclerViewOrders.OrderViewHolder> {
    private List<Order> orders;

    public AdapterRecyclerViewOrders() {
        this.orders = new ArrayList<>();
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_order_recycler, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int i) {
        orderViewHolder.bindOrder(orders.get(i));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewOrderName;
        private TextView textViewOrderDescription;
        private TextView textViewOrderDate;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewOrderDate = itemView.findViewById(R.id.cell_order_date);
            textViewOrderDescription = itemView.findViewById(R.id.cell_order_description);
            textViewOrderName = itemView.findViewById(R.id.cell_order_name);
        }

        public void bindOrder(Order order){
            textViewOrderDate.setText(order.getDate().toString());
            textViewOrderDescription.setText(order.getNotes());
            textViewOrderName.setText(order.getName());
        }
    }
}
