package com.exedos.sajinigarments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.exedos.sajinigarments.DBqueries.categoryItemModelList;
import static com.exedos.sajinigarments.DBqueries.firebaseFirestore;
import static com.exedos.sajinigarments.DBqueries.homePageModelList;
import static com.exedos.sajinigarments.DBqueries.loadCategories;

import static com.exedos.sajinigarments.DBqueries.loadFragmentData;
import static com.exedos.sajinigarments.DBqueries.loadProductsV1;
import static com.exedos.sajinigarments.DBqueries.productV1ModelList;


public class ProductsVone extends AppCompatActivity {

    private FirebaseUser currentUser;

    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    private ImageView noInternetConnection;
    private Button retryBtn;
    private List<ProductVoneModel> productVoneModelList = new ArrayList<>();
    private ProductV1GridAdapter productV1GridAdapter;
    private GridView gridV1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_vone);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(android.R.color.transparent);
        invalidateOptionsMenu();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Our Products");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        noInternetConnection = findViewById(R.id.no_internet_connection);
        retryBtn = findViewById(R.id.retry_btn);

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        gridV1 = (GridView) findViewById(R.id.productV1Grid);






        if(networkInfo != null && networkInfo.isConnected() == true) {

            noInternetConnection.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);

           if (productV1ModelList.size() == 0){
                loadProductsV1(gridV1,getBaseContext());
            }else {
                productV1GridAdapter = new ProductV1GridAdapter(productV1ModelList);
                productV1GridAdapter.notifyDataSetChanged();
            }
            gridV1.setAdapter(productV1GridAdapter);



        }
        else{
            Glide.with(this).load(R.drawable.nointernet_image).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);

        }


        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadPage();
            }
        });

    }



  /*  public void loadProductsV1() {

        firebaseFirestore.collection("Products_v1").document("All_products").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();

                    List<ProductVoneModel> productVoneModelList = new ArrayList<>();

                    long no_of_products = (long) documentSnapshot.get("no_of_products");
                    for (long x = 1; x < no_of_products+1; x++) {
                        productVoneModelList.add(new ProductVoneModel(documentSnapshot.get("product_id_" + x).toString()
                                , documentSnapshot.get("product_image_" + x).toString()
                                , documentSnapshot.get("product_title_" + x).toString()
                                , documentSnapshot.get("product_description_" + x).toString()
                                , documentSnapshot.get("product_summary_" + x).toString()
                                , documentSnapshot.get("product_price_" + x).toString()
                                , documentSnapshot.get("product_stock_" + x).toString()));

                    }
                    productV1GridAdapter = new ProductV1GridAdapter(productVoneModelList);
                    gridV1.setAdapter(productV1GridAdapter);
                    productV1GridAdapter.notifyDataSetChanged();

                } else {
                    String error = task.getException().getMessage();
                    Toast.makeText(ProductsVone.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });





    }


   */




    @Override
    protected void onStart() {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        super.onStart();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }







    private void reloadPage(){
        networkInfo = connectivityManager.getActiveNetworkInfo();
        productVoneModelList.clear();

        if(networkInfo != null && networkInfo.isConnected() == true) {

            noInternetConnection.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);

            gridV1.setVisibility(View.VISIBLE);

           productV1GridAdapter = new ProductV1GridAdapter(productV1ModelList);
           productV1GridAdapter.notifyDataSetChanged();

           gridV1.setAdapter(productV1GridAdapter);

           loadProductsV1(gridV1,getBaseContext());

        }
        else{
            Toast.makeText(this,"Check Your Internet Connection..",Toast.LENGTH_SHORT).show();
            gridV1.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.nointernet_image).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);

        }
    }




}