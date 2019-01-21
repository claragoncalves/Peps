package com.claragoncalves.peps.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.OrderContainer;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerViewOrderContainers extends RecyclerView.Adapter<AdapterRecyclerViewOrderContainers.OrderContainerViewHolder> {
    private List<OrderContainer> orderContainers;

    public AdapterRecyclerViewOrderContainers() {
        this.orderContainers = new ArrayList<>();
    }

    public void setOrderContainers(List<OrderContainer> orderContainers) {
        this.orderContainers = orderContainers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderContainerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrderContainerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_order_container_recycler, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderContainerViewHolder orderContainerViewHolder, int i) {
        orderContainerViewHolder.bindOrder(orderContainers.get(i));
    }

    @Override
    public int getItemCount() {
        return orderContainers.size();
    }

    public class OrderContainerViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewOrderName;
        private TextView textViewOrderDate;
        private RecyclerView recyclerViewProducts;
        private AdapterRecyclerViewOrderProducts adapter;

        public OrderContainerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewOrderName = itemView.findViewById(R.id.cell_order_container_order_name);
            textViewOrderDate = itemView.findViewById(R.id.cell_order_container_order_date);
            recyclerViewProducts = itemView.findViewById(R.id.cell_order_container_order_recycler_products);
            recyclerViewProducts.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false));
            adapter = new AdapterRecyclerViewOrderProducts();
            recyclerViewProducts.setAdapter(adapter);
        }

        public void bindOrder(OrderContainer orderContainer){
            textViewOrderName.setText(orderContainer.getOrder().getName());
            textViewOrderDate.setText(orderContainer.getOrder().getDate().toString());
            adapter.setProducts(orderContainer.getProducts());
        }
    }
}
