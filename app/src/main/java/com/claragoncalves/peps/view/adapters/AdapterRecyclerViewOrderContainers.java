package com.claragoncalves.peps.view.adapters;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.OrderContainer;
import com.claragoncalves.peps.model.pojo.Product;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerViewOrderContainers extends RecyclerView.Adapter<AdapterRecyclerViewOrderContainers.OrderContainerViewHolder> {
    private List<OrderContainer> orderContainers;
    private OrderContainerListener listener;

    public AdapterRecyclerViewOrderContainers(OrderContainerListener listener) {
        this.orderContainers = new ArrayList<>();
        this.listener = listener;
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
        private TextView textViewOrderTotal;

        public OrderContainerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewOrderName = itemView.findViewById(R.id.cell_order_container_order_name);
            textViewOrderDate = itemView.findViewById(R.id.cell_order_container_order_date);
            textViewOrderTotal = itemView.findViewById(R.id.cell_order_container_order_total);
            recyclerViewProducts = itemView.findViewById(R.id.cell_order_container_order_recycler_products);
            recyclerViewProducts.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false));
            adapter = new AdapterRecyclerViewOrderProducts();
            recyclerViewProducts.setAdapter(adapter);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewProducts.setDrawingCacheEnabled(true);
                    recyclerViewProducts.buildDrawingCache();
                    Bitmap bitmap = recyclerViewProducts.getDrawingCache();
                    listener.shareOrderDetails(bitmap);
                }
            });
        }

        public void bindOrder(OrderContainer orderContainer){
            textViewOrderName.setText(orderContainer.getOrder().getName());
            textViewOrderDate.setText(orderContainer.getOrder().getDateWithFormat());
            adapter.setProducts(orderContainer.getProducts());
            textViewOrderTotal.setText("$" + calculateOrderTotal(orderContainer.getProducts()).toString());
        }

        public Double calculateOrderTotal(List<Product> products){
            Double total = 0.0;
            for (Product product : products) {
                total = total + product.getSellPrice() * product.getQuantity();
            }
            return total;
        }
    }


    public interface OrderContainerListener{
        public void shareOrderDetails(Bitmap bitmap);
    }

}
