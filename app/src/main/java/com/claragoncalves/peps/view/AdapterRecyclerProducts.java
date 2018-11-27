package com.claragoncalves.peps.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.claragoncalves.peps.R;
import com.claragoncalves.peps.model.pojo.Product;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerProducts extends RecyclerView.Adapter<AdapterRecyclerProducts.ProductsViewHolder>{
    public static final String CATEGORY_BUY = "categoryBuy";
    public static final String CATEGORY_SELL = "categorySell";
    private List<Product> products;
    private String categoryName;
    private SumInterface sumInterface;

    public AdapterRecyclerProducts(String categoryName, SumInterface sumInterface) {
        this.products = new ArrayList<>();
        this.categoryName = categoryName;
        this.sumInterface = sumInterface;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductsViewHolder productsViewHolder = new ProductsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_product,parent,false));
        return productsViewHolder;
    }

    @Override
    public void onBindViewHolder(final ProductsViewHolder holder, final int position) {
        holder.bindProduct(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewPrice;
        private DecimalFormat df;
        private EditText editTextQuantity;

        private ProductsViewHolder(final View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewCellProductName);
            textViewDescription = itemView.findViewById(R.id.textViewCellProductDescription);
            textViewPrice = itemView.findViewById(R.id.textViewCellProductPrice);
            editTextQuantity = itemView.findViewById(R.id.editTextCellProductQuantity);
            df = new DecimalFormat("####0.00");

            editTextQuantity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == EditorInfo.IME_ACTION_DONE && !editTextQuantity.getText().toString().equals("")) {
                        products.get(getAdapterPosition()).setQuantity(Integer.parseInt(editTextQuantity.getText().toString()));
                        sumInterface.quantityTapped(calculateTotal());
                        notifyDataSetChanged();
                        InputMethodManager inputMethodManager = (InputMethodManager) itemView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(editTextQuantity.getWindowToken(), 0);
                        return true;
                    }
                    return false;
                }
            });

            editTextQuantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (!editable.toString().equals("")) {
                        products.get(getAdapterPosition()).setQuantity(Integer.parseInt(editable.toString()));
                        sumInterface.quantityTapped(calculateTotal());
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TapAction tapAction = (TapAction) itemView.getContext();
                    tapAction.goToDetail(products.get(getAdapterPosition()).getId());
                }
            });
        }

        private void bindProduct(Product product){
            textViewName.setText(product.getName());
            textViewDescription.setText(product.getDescription());
            if (product.getQuantity() >0) {
                editTextQuantity.setText(product.getQuantity().toString());
            } else {
                editTextQuantity.setText("");
            }
            if (categoryName.equals(CATEGORY_BUY)){
                textViewPrice.setText("$" + df.format(product.getBuyPrice()));
            }else {
                textViewPrice.setText("$" + df.format(product.getSellPrice()));
            }
        }
    }

    public interface TapAction{
        public void goToDetail(Integer idProduct);
    }

    public interface SumInterface{
        public void quantityTapped(String total);
    }

    private String calculateTotal(){
        Double total = 0.0;
        if (categoryName.equals(CATEGORY_BUY)) {
            for (Product product : products) {
                total = total + product.getBuyPrice() * product.getQuantity();
            }
        }else {
           for (Product product : products) {
                total = total + product.getSellPrice() * product.getQuantity();
           }
        }

        DecimalFormat df = new DecimalFormat("####0.00");

        return " " + categoryName + ": $" + df.format(total);
    }
}
