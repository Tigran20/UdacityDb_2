package com.alextroy.udacitydb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alextroy.udacitydb.R;
import com.alextroy.udacitydb.model.Product;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {

    private Context context;
    private List<Product> list;

    public InventoryAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inventory_item, parent, false);
        return new InventoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        Product product = list.get(position);

        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());
        holder.productQuantity.setText(String.valueOf(product.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InventoryViewHolder extends RecyclerView.ViewHolder {

        public TextView productName;
        public TextView productPrice;
        public TextView productQuantity;

        public InventoryViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productQuantity = itemView.findViewById(R.id.product_quantity);
        }
    }
}
