package com.exedos.sajinigarments;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class MyOrdersFragment extends Fragment {

    ;


    public MyOrdersFragment() {
        // Required empty public constructor
    }

    private RecyclerView myOrdersRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_orders, container, false);


        myOrdersRecyclerView = view.findViewById(R.id.myorders_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myOrdersRecyclerView.setLayoutManager(layoutManager);


        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.placeholder2, 3, "collar t shirt 1", "Delivered on Dec 30 2020"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.placeholder2, 2, "collar t shirt 2", "Delivered on Jan 27 2021"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.placeholder2, 3, "collar t shirt 3", "Delivered on Jan 29 2021"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.placeholder2, 1, "collar t shirt 4", "Delivered on Feb 1 2021"));

        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderItemModelList);
        myOrdersRecyclerView.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();

        return view;
    }
}