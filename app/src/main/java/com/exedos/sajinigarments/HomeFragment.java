package com.exedos.sajinigarments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.ArrayListMultimap;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;
import static com.exedos.sajinigarments.DBqueries.categoryItemModelList;
import static com.exedos.sajinigarments.DBqueries.firebaseFirestore;
import static com.exedos.sajinigarments.DBqueries.homePageModelList;
import static com.exedos.sajinigarments.DBqueries.loadCategories;
import static com.exedos.sajinigarments.DBqueries.loadFragmentData;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private HomePageAdapter adapter;
    private List<CategoryItemModel> categoryFakeList = new ArrayList<>();
    private RecyclerView mulRecycler;
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    private ImageView noInternetConnection;
    public static SwipeRefreshLayout swipeRefreshLayout;
    private List<HomePageModel> homePageModelFakeList = new ArrayList<>();
    private Button retryBtn;
    private TextView homeMarquee;





  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        noInternetConnection = view.findViewById(R.id.no_internet_connection);




      ///category fake list
      categoryRecyclerView = view.findViewById(R.id.category_layout_recyclerview);
      LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
      layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
      categoryRecyclerView.setLayoutManager(layoutManager);

      mulRecycler = view.findViewById(R.id.mul_recyclerview);
      LinearLayoutManager mulLayoutManager = new LinearLayoutManager(getContext());
      mulLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
      mulRecycler.setLayoutManager(mulLayoutManager);

      retryBtn = view.findViewById(R.id.retry_btn);
      homeMarquee = view.findViewById(R.id.home_marquee);
      homeMarquee.setSelected(true);



      categoryFakeList.add(new CategoryItemModel("sajini","","#e6e7e8"));
      categoryFakeList.add(new CategoryItemModel("","","#e6e7e8"));
      categoryFakeList.add(new CategoryItemModel("","","#e6e7e8"));
      categoryFakeList.add(new CategoryItemModel("","","#e6e7e8"));
      categoryFakeList.add(new CategoryItemModel("","","#e6e7e8"));
      categoryFakeList.add(new CategoryItemModel("","","#e6e7e8"));


      ///category fake list

      ///Home page fake list

      List<SliderModel> sliderModelFakeList = new ArrayList<>();
      sliderModelFakeList.add(new SliderModel("null","#e6e7e8"));
      sliderModelFakeList.add(new SliderModel("null","#e6e7e8"));
      sliderModelFakeList.add(new SliderModel("null","#e6e7e8"));
      sliderModelFakeList.add(new SliderModel("null","#e6e7e8"));
      sliderModelFakeList.add(new SliderModel("null","#e6e7e8"));


      List<HorizontalProductScrollModel> horizontalProductScrollModelFakeList = new ArrayList<>();
      horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
      horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
      horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
      horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
      horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
      horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));


      homePageModelFakeList.add(new HomePageModel(0,sliderModelFakeList));
      homePageModelFakeList.add(new HomePageModel(1,"","#e6e7e8",horizontalProductScrollModelFakeList, new ArrayList<WishListModel>()));
      homePageModelFakeList.add(new HomePageModel(2,"","#e6e7e8",horizontalProductScrollModelFakeList));
      homePageModelFakeList.add(new HomePageModel(3,"","#e6e7e8"));

      ///Home page fake list


      categoryAdapter = new CategoryAdapter(categoryFakeList);
      categoryRecyclerView.setAdapter(categoryAdapter);

      adapter = new HomePageAdapter(homePageModelFakeList);
      mulRecycler.setAdapter(adapter);

      connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
      networkInfo = connectivityManager.getActiveNetworkInfo();


      if(networkInfo != null && networkInfo.isConnected() == true) {

          MainActivity.drawer1.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
          noInternetConnection.setVisibility(View.GONE);
          categoryRecyclerView.setVisibility(View.VISIBLE);
          mulRecycler.setVisibility(View.VISIBLE);
          retryBtn.setVisibility(View.GONE);



          if (categoryItemModelList.size() == 0){
              loadCategories(categoryRecyclerView,getContext());

          }else {
              categoryAdapter = new CategoryAdapter(categoryItemModelList);
              categoryAdapter.notifyDataSetChanged();
          }
          categoryRecyclerView.setAdapter(categoryAdapter);



          if (homePageModelList.size() == 0){
              loadFragmentData(mulRecycler,getContext());

          }else {
              adapter = new HomePageAdapter(homePageModelList);
              adapter.notifyDataSetChanged();
          }
          mulRecycler.setAdapter(adapter);

      }
      else{
          MainActivity.drawer1.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
          Glide.with(this).load(R.drawable.nointernet_image).into(noInternetConnection);
          noInternetConnection.setVisibility(View.VISIBLE);
          retryBtn.setVisibility(View.VISIBLE);
          categoryRecyclerView.setVisibility(View.GONE);
          mulRecycler.setVisibility(View.GONE);


      }


      ///// refresh Layout

      swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
              swipeRefreshLayout.setRefreshing(true);
              reloadPage();

          }
      });

      retryBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              reloadPage();
          }
      });

      return view;
    }


    private void reloadPage(){
        networkInfo = connectivityManager.getActiveNetworkInfo();
        categoryItemModelList.clear();
        homePageModelList.clear();

        if(networkInfo != null && networkInfo.isConnected() == true) {
            MainActivity.drawer1.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            noInternetConnection.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);

            categoryRecyclerView.setVisibility(View.VISIBLE);
            mulRecycler.setVisibility(View.VISIBLE);
            categoryRecyclerView.setAdapter(categoryAdapter);
            mulRecycler.setAdapter(adapter);


            loadCategories(categoryRecyclerView,getContext());
            loadFragmentData(mulRecycler,getContext());

        }
        else{
            Toast.makeText(getContext(),"Check Your Internet Connection..",Toast.LENGTH_SHORT).show();
            MainActivity.drawer1.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            categoryRecyclerView.setVisibility(View.GONE);
            mulRecycler.setVisibility(View.GONE);
            Glide.with(getContext()).load(R.drawable.nointernet_image).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);




            swipeRefreshLayout.setRefreshing(false);

        }
    }




}