package com.exedos.sajinigarments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exedos.sajinigarments.MyAccountFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 */
public class MyCartFragment extends Fragment {

    NavigationView navigationView;




    public MyCartFragment(){

    }



    private RecyclerView cartItemsRecyclerView;
    private Button continueBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);





        cartItemsRecyclerView = view.findViewById(R.id.cart_items_recyclerview);
        continueBtn = view.findViewById(R.id.cart_continue_btn);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        cartItemsRecyclerView.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.placeholder2,"Round collar","Rs.599/-","Rs.999/-",1,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.placeholder2,"Neck collar","Rs.550/-","Rs.859/-",1,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.placeholder2,"Hoodie","Rs.799/-","Rs.1299/-",1,0));
        cartItemModelList.add(new CartItemModel(1,"Price (3 Items )","Rs.1948/-","Free","Rs.1948/-","You Saved Rs.1209/-"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartItemsRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();


        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent = new Intent(getContext(), AddAddressActivity.class);
                getContext().startActivity(deliveryIntent);
            }
        });
        HomeFragment.swipeRefreshLayout.setRefreshing(false);


        return view;


    }






}