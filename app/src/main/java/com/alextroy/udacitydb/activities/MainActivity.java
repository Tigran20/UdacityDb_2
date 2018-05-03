package com.alextroy.udacitydb.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alextroy.udacitydb.R;
import com.alextroy.udacitydb.adapter.InventoryAdapter;
import com.alextroy.udacitydb.model.Data;
import com.alextroy.udacitydb.model.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InventoryAdapter adapter;
    private List<Product> list;
    private FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_list);

        init();
    }

    private void init() {
        list = Data.getProductsData(this);

        recyclerView = findViewById(R.id.recycler_view);
        actionButton = findViewById(R.id.fab);

        adapter = new InventoryAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}
