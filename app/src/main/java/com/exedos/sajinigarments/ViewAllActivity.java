package com.exedos.sajinigarments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;

    private RecyclerView recyclerView;
    private GridView gridView;


    public static List<WishListModel> wishListModelList;
    public static  List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(android.R.color.transparent);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        recyclerView = findViewById(R.id.Viewall_recyclerView);
        gridView = findViewById(R.id.Viewall_grid_view);
        int layout_code = getIntent().getIntExtra("layout_code", -1);


        if(layout_code == 0) {

            getSupportActionBar().setTitle(getIntent().getStringExtra("title"));


            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            WishlistAdapter adapter = new WishlistAdapter(wishListModelList, false);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        else if(layout_code == 1) {


            getSupportActionBar().setTitle("Customize Products");
            gridView.setVisibility(View.VISIBLE);
            CustomizeGridAdapter customizeGridAdapter = new CustomizeGridAdapter(horizontalProductScrollModelList);
            gridView.setAdapter(customizeGridAdapter);
        }


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