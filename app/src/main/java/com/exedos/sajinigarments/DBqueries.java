package com.exedos.sajinigarments;

import android.content.Context;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DBqueries {



    public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public static String email,fullname,profile;

    public static List<CategoryItemModel> categoryItemModelList = new ArrayList<>();
    public static List<HomePageModel> homePageModelList = new ArrayList<>();
    public static List<ProductVoneModel> productV1ModelList = new ArrayList<>();


    public static void loadCategories(RecyclerView categoryRecyclerView, final Context context){



        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                categoryItemModelList.add(new CategoryItemModel(documentSnapshot.get("icon").toString(),documentSnapshot.get("categoryName").toString(),documentSnapshot.get("backgroundColor").toString()));
                            }
                            CategoryAdapter categoryAdapter = new CategoryAdapter(categoryItemModelList);
                            categoryRecyclerView.setAdapter(categoryAdapter);
                            categoryAdapter.notifyDataSetChanged();

                        }else {
                            String error = task.getException().getMessage();
                            Toast.makeText(context, error,Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }

    public static void loadFragmentData(RecyclerView mulRecycler, Context context){
        firebaseFirestore.collection("HOME_CONTENT").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        if ((long)documentSnapshot.get("view_type") == 0 ){
                            List<SliderModel> sliderModelList = new ArrayList<>();
                            long no_of_banners = (long)documentSnapshot.get("no_of_banners");
                            for(long x = 1;x < no_of_banners + 1;x++){
                                sliderModelList.add(new SliderModel(documentSnapshot.get("banner_"+x).toString()
                                        ,documentSnapshot.get("banner_"+ x +"_background").toString()));
                            }
                            homePageModelList.add(new HomePageModel(0,sliderModelList));
                        }
                        else if((long)documentSnapshot.get("view_type") == 1){
                            List<WishListModel> viewAllProductList = new ArrayList<>();

                            List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();

                            long no_of_products = (long)documentSnapshot.get("number_of_products");
                            for(long x = 1;x <= no_of_products;x++){
                                horizontalProductScrollModelList.add(new HorizontalProductScrollModel(documentSnapshot.get("product_ID_"+x).toString()
                                        ,documentSnapshot.get("product_image_" +x).toString()
                                        ,documentSnapshot.get("product_title_"+x).toString()
                                        ,documentSnapshot.get("product_subtitle_"+x).toString()
                                        ,documentSnapshot.get("product_price_"+x).toString()));

                                viewAllProductList.add(new WishListModel(documentSnapshot.get("product_image_"+x).toString()
                                        ,documentSnapshot.get("product_full_title_"+x).toString()
                                        ,documentSnapshot.get("average_rating_"+x).toString()
                                        ,(long)documentSnapshot.get("total_ratings_"+x)
                                        ,documentSnapshot.get("product_price_"+x).toString()
                                        ,documentSnapshot.get("cutted_price_"+x).toString()
                                        ,(boolean)documentSnapshot.get("COD_"+x)));

                            }
                            homePageModelList.add(new HomePageModel(1,documentSnapshot.get("layout_title").toString(),documentSnapshot.get("layout_background").toString(),horizontalProductScrollModelList, viewAllProductList));




                        }
                        else if((long)documentSnapshot.get("view_type") == 2){
                            List<HorizontalProductScrollModel> customizeGridModelList = new ArrayList<>();

                            long no_of_products = (long)documentSnapshot.get("no_of_products");
                            for(long x = 1;x <= no_of_products;x++){
                                customizeGridModelList.add(new HorizontalProductScrollModel(documentSnapshot.get("product_ID_"+x).toString()
                                        ,documentSnapshot.get("product_" +x).toString()
                                        ,documentSnapshot.get("product_"+x+"_title").toString()
                                        ,documentSnapshot.get("product_"+x+"_description").toString()
                                        ,documentSnapshot.get("product_"+x+"_price").toString()));

                            }
                            homePageModelList.add(new HomePageModel(2,documentSnapshot.get("layout_title").toString(),documentSnapshot.get("layout_background").toString(),customizeGridModelList));



                        }
                        else if((long)documentSnapshot.get("view_type") == 3){

                            homePageModelList.add(new HomePageModel(3,documentSnapshot.get("bulk_image").toString(),documentSnapshot.get("background_color").toString()));

                        }
                    }
                    HomePageAdapter homePageadapter = new HomePageAdapter(homePageModelList);
                    mulRecycler.setAdapter(homePageadapter);
                    homePageadapter.notifyDataSetChanged();

                    HomeFragment.swipeRefreshLayout.setRefreshing(false);

                }else {
                    String error = task.getException().getMessage();
                    Toast.makeText(context, error,Toast.LENGTH_SHORT).show();

                }

            }

        }
        );

    }

    public static void loadProductsV1(GridView gridV1, Context context){

        firebaseFirestore.collection("Products_v1").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    List<ProductVoneModel> productVoneModelList = new ArrayList<>();
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){


                        if ((long)documentSnapshot.get("view_type") == 0 ) {

                            long no_of_products = (long) documentSnapshot.get("no_of_products");
                            for (long x = 1; x <= no_of_products; x++) {
                                productVoneModelList.add(new ProductVoneModel(documentSnapshot.get("product_id_" + x).toString()
                                        , documentSnapshot.get("product_image_" + x).toString()
                                        , documentSnapshot.get("product_title_" + x).toString()
                                        , documentSnapshot.get("product_description_" + x).toString()
                                        , documentSnapshot.get("product_summary_" + x).toString()
                                        , documentSnapshot.get("product_price_" + x).toString()
                                        , documentSnapshot.get("product_stock_" + x).toString())
                                );

                            }


                        }else if((long)documentSnapshot.get("view_type") == 1 ){

                            long no_of_products = (long) documentSnapshot.get("no_of_products");
                            for (long x = 1; x <= no_of_products; x++) {
                                productVoneModelList.add(new ProductVoneModel(documentSnapshot.get("product_id_" + x).toString()
                                        , documentSnapshot.get("product_image_" + x).toString()
                                        , documentSnapshot.get("product_title_" + x).toString()
                                        , documentSnapshot.get("product_description_" + x).toString()
                                        , documentSnapshot.get("product_summary_" + x).toString()
                                        , documentSnapshot.get("product_price_" + x).toString()
                                        , documentSnapshot.get("product_stock_" + x).toString())
                                );

                            }
                        }else if((long)documentSnapshot.get("view_type") == 2 ){

                            long no_of_products = (long) documentSnapshot.get("no_of_products");
                            for (long x = 1; x <= no_of_products; x++) {
                                productVoneModelList.add(new ProductVoneModel(documentSnapshot.get("product_id_" + x).toString()
                                        , documentSnapshot.get("product_image_" + x).toString()
                                        , documentSnapshot.get("product_title_" + x).toString()
                                        , documentSnapshot.get("product_description_" + x).toString()
                                        , documentSnapshot.get("product_summary_" + x).toString()
                                        , documentSnapshot.get("product_price_" + x).toString()
                                        , documentSnapshot.get("product_stock_" + x).toString())
                                );

                            }
                        }

                    else if((long)documentSnapshot.get("view_type") == 3 ){

                        long no_of_products = (long) documentSnapshot.get("no_of_products");
                        for (long x = 1; x <= no_of_products; x++) {
                            productVoneModelList.add(new ProductVoneModel(documentSnapshot.get("product_id_" + x).toString()
                                    , documentSnapshot.get("product_image_" + x).toString()
                                    , documentSnapshot.get("product_title_" + x).toString()
                                    , documentSnapshot.get("product_description_" + x).toString()
                                    , documentSnapshot.get("product_summary_" + x).toString()
                                    , documentSnapshot.get("product_price_" + x).toString()
                                    , documentSnapshot.get("product_stock_" + x).toString())
                            );


                        }
                    }


                    }

                    ProductV1GridAdapter productV1GridAdapter = new ProductV1GridAdapter(productVoneModelList);
                    gridV1.setAdapter(productV1GridAdapter);
                    productV1GridAdapter.notifyDataSetChanged();

                } else {
                    String error = task.getException().getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

}
