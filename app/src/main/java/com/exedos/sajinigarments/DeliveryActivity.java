package com.exedos.sajinigarments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {


    androidx.appcompat.widget.Toolbar toolbar;

    private RecyclerView deliveryRecyclerView;
    private Button changeORaddNewAddressBtn;
    public static final int SELECT_ADDRESS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Delivery");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        changeORaddNewAddressBtn = findViewById(R.id.change_or__add_address_btn);
        deliveryRecyclerView = findViewById(R.id.delivery_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        deliveryRecyclerView.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.placeholder2,"Round collar","Rs.599/-","Rs.999/-",1,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.placeholder2,"Neck collar","Rs.550/-","Rs.859/-",1,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.placeholder2,"Hoodie","Rs.799/-","Rs.1299/-",1,0));
        cartItemModelList.add(new CartItemModel(1,"Price (3 Items )","Rs.1948/-","Free","Rs.1948/-","You Saved Rs.1209/-"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        deliveryRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        changeORaddNewAddressBtn.setVisibility(View.VISIBLE);
        changeORaddNewAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myAddressItent = new Intent(DeliveryActivity.this,MyAddressActivity.class);
                myAddressItent.putExtra("MODE",SELECT_ADDRESS);
                startActivity(myAddressItent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}