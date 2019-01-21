package com.claragoncalves.peps.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Product;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerViewOrderProducts extends RecyclerView.Adapter<AdapterRecyclerViewOrderProducts.OrderProductViewHolder> {
    private List<Product> products;

    public AdapterRecyclerViewOrderProducts() {
        this.products = new ArrayList<>();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrderProductViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_product_order_detail_recycler, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderProductViewHolder orderProductViewHolder, int i) {
        orderProductViewHolder.bindProduct(products.get(i));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class OrderProductViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewProductName;
        private TextView textViewProductQuantity;

        public OrderProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.cell_product_order_detail_recycler_name);
            textViewProductQuantity = itemView.findViewById(R.id.cell_product_order_detail_recycler_quantity);
        }

        public void bindProduct(Product product){
            textViewProductName.setText(product.getName());
            textViewProductQuantity.setText(product.getQuantity().toString());
        }
    }
}
