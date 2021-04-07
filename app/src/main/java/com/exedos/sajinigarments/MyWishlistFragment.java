package com.exedos.sajinigarments;

import android.content.Intent;
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
public class MyWishlistFragment extends Fragment {


    public MyWishlistFragment() {
        // Required empty public constructor
    }
    private RecyclerView wishlistRecyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view =  inflater.inflate(R.layout.fragment_my_wishlist, container, false);


        wishlistRecyclerView = view.findViewById(R.id.my_wishlist_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wishlistRecyclerView.setLayoutManager(linearLayoutManager);

        List<WishListModel> wishListModelList = new ArrayList<>();
      /*  wishListModelList.add(new WishListModel(R.drawable.customize1,"T-Shirt1","4.5",30,"Rs.599/-","Rs.999/-","Cash On Delivery"));
        wishListModelList.add(new WishListModel(R.drawable.customize2,"T-Shirt2","3.5",140,"Rs.590/-","Rs.990/-","Cash On Delivery"));
        wishListModelList.add(new WishListModel(R.drawable.customize3,"T-Shirt3","4.7",700,"Rs.999/-","Rs.1299/-","Cash On Delivery"));
        wishListModelList.add(new WishListModel(R.drawable.customize4,"T-Shirt4","5",900,"Rs.799/-","Rs.2099/-","Cash On Delivery"));
*/
       WishlistAdapter wishlistAdapter = new WishlistAdapter(wishListModelList,true);
       wishlistRecyclerView.setAdapter(wishlistAdapter);
       wishlistAdapter.notifyDataSetChanged();


       return view;

    }
}