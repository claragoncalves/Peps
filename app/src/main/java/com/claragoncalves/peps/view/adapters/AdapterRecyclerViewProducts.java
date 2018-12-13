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

public class AdapterRecyclerViewProducts extends RecyclerView.Adapter<AdapterRecyclerViewProducts.ProductViewHolder> {
    private List<Product> products;
    private ProductSelectedListener listener;

    public AdapterRecyclerViewProducts(ProductSelectedListener listener) {
        this.products = new ArrayList<>();
        this.listener = listener;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ProductViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_product_recycler, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        productViewHolder.bindProduct(products.get(i));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewProductName;
        private TextView textViewProductDescription;
        private TextView textViewProductBuyPrice;
        private TextView textViewProductSellPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.cell_product_recycler_textview_name);
            textViewProductDescription = itemView.findViewById(R.id.cell_product_recycler_textview_description);
            textViewProductBuyPrice = itemView.findViewById(R.id.cell_product_recycler_textview_buy_price);
            textViewProductSellPrice = itemView.findViewById(R.id.cell_product_recycler_textview_sell_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.productSelected(products.get(getAdapterPosition()).getId());
                }
            });
        }

        public void bindProduct(Product product){
            textViewProductName.setText(product.getName());
            textViewProductDescription.setText(product.getDescription());
            textViewProductBuyPrice.setText(product.getBuyPrice().toString());
            textViewProductSellPrice.setText(product.getSellPrice().toString());
        }
    }

    public interface ProductSelectedListener{
        void productSelected(Integer productId);
    }
}
