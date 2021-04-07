package com.exedos.sajinigarments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Men extends AppCompatActivity {
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    androidx.appcompat.widget.Toolbar toolbar;
    GridView grid1;

    private RecyclerView categoryRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men);


        toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(android.R.color.transparent);

//////Category layout

        categoryRecyclerView = findViewById(R.id.category_layout_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        List<CategoryItemModel> categoryItemModelList = new ArrayList<>();
      /*  categoryItemModelList.add(new CategoryItemModel(R.drawable.men,"MEN","#3F51B5"));
        categoryItemModelList.add(new CategoryItemModel(R.drawable.women,"WOMEN","#FA4D88"));
        categoryItemModelList.add(new CategoryItemModel(R.drawable.kidsicon,"KIDS","#12D12C"));

        categoryItemModelList.add(new CategoryItemModel(R.drawable.yogi,"TRENIDNG","#3F51B5"));
        categoryItemModelList.add(new CategoryItemModel(R.drawable.yogi,"CUSTOMIZED","#FA4D88"));
        categoryItemModelList.add(new CategoryItemModel(R.drawable.yogi,"BULK","#12D12C"));
*/
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryItemModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        //////Category layout


        //---------- main menu navigation code  ------------   //

        navigationView = (NavigationView) findViewById(R.id.navmenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView.bringToFront();
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_home:

                        Intent intent = new Intent(Men.this, MainActivity.class);

                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_info:
                        Intent intent3 = new Intent(Men.this, MainActivity.class);
                        startActivity(intent3);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_shop:
                        Intent intent1 = new Intent(Men.this, MainActivity.class);
                        startActivity(intent1);

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;



                    case R.id.menu_Contact:
                        Intent intent5 = new Intent(Men.this, MainActivity.class);
                        startActivity(intent5);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }
                return false;
            }
        });
        //---------- main menu navigation code  ------------   //


        //---------- Products code  ------------   //

        List<ProductFixedModel> productFixedModelList = new ArrayList<>();
        productFixedModelList.add(new ProductFixedModel(R.drawable.placeholder2,"Round Neck", "Burgundy round", "RS 599/-"));
        productFixedModelList.add(new ProductFixedModel(R.drawable.placeholder2,"Collar", "Burgundy round", "RS 599/-"));
        productFixedModelList.add(new ProductFixedModel(R.drawable.placeholder2,"full sleeve", "Burgundy round", "RS 599/-"));
        productFixedModelList.add(new ProductFixedModel(R.drawable.placeholder2,"Round Neck", "Burgundy round", "RS 599/-"));
        productFixedModelList.add(new ProductFixedModel(R.drawable.placeholder2,"Round Neck", "Burgundy round", "RS 599/-"));
        productFixedModelList.add(new ProductFixedModel(R.drawable.placeholder2,"Collar", "Burgundy round", "RS 599/-"));
        productFixedModelList.add(new ProductFixedModel(R.drawable.placeholder2,"full sleeve", "Burgundy round", "RS 599/-"));
        productFixedModelList.add(new ProductFixedModel(R.drawable.placeholder2,"Round Neck", "Burgundy round", "RS 599/-"));



        grid1 = (GridView) findViewById(R.id.productgrid);
        grid1.setAdapter(new GridProductAdapter(productFixedModelList));

        //---------- Products code  ------------   //

    }
}