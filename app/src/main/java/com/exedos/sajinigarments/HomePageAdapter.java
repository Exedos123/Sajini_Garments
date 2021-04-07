package com.exedos.sajinigarments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class HomePageAdapter extends RecyclerView.Adapter {
    private List<HomePageModel> homePageModelList;
    private RecyclerView.RecycledViewPool recycledViewPool;

    private int lastPosition = -1;



    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }


    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()){

            case 0:
                return HomePageModel.BANNER_SLIDER;
            case 1:
                return HomePageModel.HORIZONTAL_TRENDING;
            case 2:
                return HomePageModel.CUSTOMIZE_GRIDLAYOUT;

            case 3:
                return HomePageModel.BULK_CARD;



            default:
                return -1;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case HomePageModel.BANNER_SLIDER:
                View bannerSliderView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.slider_banner_layout,viewGroup,false);
                return new SgBannerSliderViewHolder(bannerSliderView);

            case HomePageModel.HORIZONTAL_TRENDING:
                View horizontalTrendingView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_layout,viewGroup,false);
                return new HorizontalTrendingViewHolder(horizontalTrendingView);
            case HomePageModel.CUSTOMIZE_GRIDLAYOUT:
                View customizeGridLayout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customize_grid_layout,viewGroup,false);
                return new CustomizeGridViewHolder(customizeGridLayout);
            case HomePageModel.BULK_CARD:
                View bulkCardLayout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bulk_banner_layout,viewGroup,false);
                return new BulkCardViewHolder(bulkCardLayout);
            default:
                return null;

        }

    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (homePageModelList.get(position).getType()){
            case HomePageModel.BANNER_SLIDER:
                List<SliderModel> sliderModelList = homePageModelList.get(position).getSliderModelList();
               ((SgBannerSliderViewHolder)holder).setBannerSliderViewPager(sliderModelList);
                break;
            case HomePageModel.HORIZONTAL_TRENDING:

                String horizontalLayoutTitle = homePageModelList.get(position).getTitle();
                String color = homePageModelList.get(position).getBackgroundColor();
                List<WishListModel> viewAllProductList = homePageModelList.get(position).getViewAllProductList();

                List<HorizontalProductScrollModel> horizontalProductScrollModelList = homePageModelList.get(position).getHorizontalProductScrollModelList();
                ((HorizontalTrendingViewHolder)holder).setHorizontalTrendingLayout(horizontalProductScrollModelList, horizontalLayoutTitle, color, viewAllProductList);
                break;
            case HomePageModel.CUSTOMIZE_GRIDLAYOUT:

                String customGridLayoutTitle = homePageModelList.get(position).getTitle();
                String color1 = homePageModelList.get(position).getBackgroundColor();


                List<HorizontalProductScrollModel> customizeGridModelList = homePageModelList.get(position).getHorizontalProductScrollModelList();
                ((CustomizeGridViewHolder)holder).setCustomizeGridLayout(customizeGridModelList, customGridLayoutTitle, color1);
                break;

            case HomePageModel.BULK_CARD:
                String resource = homePageModelList.get(position).getBulkImage();
                String color2 = homePageModelList.get(position).getBackGroundColor();

                ((BulkCardViewHolder)holder).setBulkCardLayout(resource,color2);
                break;
            default:
                return;

        }

        if(lastPosition < position) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_in);
            holder.itemView.setAnimation(animation);
            lastPosition = position;
        }

    }



    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }


    public class SgBannerSliderViewHolder extends RecyclerView.ViewHolder {


        private ViewPager bannerSliderViewPager;
        private int currentPage;
        private Timer timer;
        final  private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;
        private List<SliderModel> arrangedList;

public SgBannerSliderViewHolder(@NonNull View itemView){
    super(itemView);
    bannerSliderViewPager = itemView.findViewById(R.id.banner_view_pager);


}


        private void setBannerSliderViewPager(final List<SliderModel> sliderModelList){

    currentPage = 2;

         if (timer != null){
             timer.cancel();
         }

         arrangedList = new ArrayList<>();
         for (int x = 0;x < sliderModelList.size();x++){
             arrangedList.add(x,sliderModelList.get(x));
         }

         arrangedList.add(0,sliderModelList.get(sliderModelList.size() - 2));
            arrangedList.add(1,sliderModelList.get(sliderModelList.size() - 1));
            arrangedList.add(sliderModelList.get(0));
            arrangedList.add(sliderModelList.get(1));

            SliderAdapter sliderAdapter;
            sliderAdapter = new SliderAdapter(arrangedList);
            bannerSliderViewPager.setAdapter(sliderAdapter);
            bannerSliderViewPager.setClipToPadding(false);
            bannerSliderViewPager.setPageMargin(20);

            bannerSliderViewPager.setCurrentItem(currentPage);
            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {

                    currentPage = i;


                }

                @Override
                public void onPageScrollStateChanged(int i) {

                    if(i == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(arrangedList);
                    }

                }
            };
            bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

            startBannerSlideShow(arrangedList);

            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    pageLooper(arrangedList);
                    stopBannerSlideShow();
                    if(event.getAction() == MotionEvent.ACTION_UP){
                        startBannerSlideShow(arrangedList);
                    }
                    return false;
                }
            });

        }

        private void pageLooper(List<SliderModel> sliderModelList){
            if(currentPage == sliderModelList.size() - 2){
                currentPage = 2;
                bannerSliderViewPager.setCurrentItem(currentPage,false);
            }

            if(currentPage == 1){
                currentPage = sliderModelList.size() - 3;
                bannerSliderViewPager.setCurrentItem(currentPage,false);
            }
        }

        private void startBannerSlideShow(final List<SliderModel> sliderModelList){
            Handler handler = new Handler();
            Runnable update = new Runnable() {
                @Override
                public void run() {
                    if(currentPage >= sliderModelList.size()){
                        currentPage = 1;
                    }
                    bannerSliderViewPager.setCurrentItem(currentPage++,true);
                }
            };

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);

                }

            }, DELAY_TIME, PERIOD_TIME  );
        }

        private void stopBannerSlideShow(){
            timer.cancel();
        }
    }

    public class HorizontalTrendingViewHolder extends RecyclerView.ViewHolder{


        private TextView horizontalLayoutTitle;
        private Button horizontalLayoutViewAllBtn;
        private ConstraintLayout container;
        private RecyclerView horizontalRecyclerView;


        public HorizontalTrendingViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            horizontalLayoutTitle = itemView.findViewById(R.id.horizontal_scroll_layout_title);
            horizontalLayoutViewAllBtn = itemView.findViewById(R.id.horizontal_scroll_layout_btn);
            horizontalRecyclerView = itemView.findViewById(R.id.horizontal_scroll_layout_recyclerview);
            horizontalRecyclerView.setRecycledViewPool(recycledViewPool);
        }

        private void setHorizontalTrendingLayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList,String title, String color, List<WishListModel> viewAllProductList){

            container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            horizontalLayoutTitle.setText(title);

            if(horizontalProductScrollModelList.size() > 5 ){
                horizontalLayoutViewAllBtn.setVisibility(View.VISIBLE);

                if(!title.equals("")) {
                    horizontalLayoutViewAllBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Click on anything go to productVone class
                            Intent contactIntent = new Intent(v.getContext(), ProductsVone.class);
                            v.getContext().startActivity(contactIntent);
                            //Click on anything go to productVone class


                            /*  original code for view all btn
                            ViewAllActivity.wishListModelList = viewAllProductList;
                            Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                            viewAllIntent.putExtra("layout_code", 0);
                            viewAllIntent.putExtra("title", title);
                            itemView.getContext().startActivity(viewAllIntent);

                               original code for view all btn*/
                        }
                    });
                }
            }
            else{
                horizontalLayoutViewAllBtn.setVisibility(View.INVISIBLE);
            }

            HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            horizontalRecyclerView.setLayoutManager(linearLayoutManager);

            horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
            horizontalProductScrollAdapter.notifyDataSetChanged();

        }
    }

    public class CustomizeGridViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout container;
        private TextView customizeGridTitle;
        private Button customizeGridViewAllBtn;
        private androidx.gridlayout.widget.GridLayout customizeGridProductLayout;




        public CustomizeGridViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            customizeGridTitle = itemView.findViewById(R.id.grid_layout_customize_products);
            customizeGridViewAllBtn = itemView.findViewById(R.id.grid_product_layout_viewall_btn);
            customizeGridProductLayout = itemView.findViewById(R.id.custom_grid_layout);

        }
        private void setCustomizeGridLayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList,String title, String color1){
            container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color1)));
            customizeGridTitle.setText(title);


            for (int x = 0; x < 4; x++ ) {
                ImageView productImage = customizeGridProductLayout.getChildAt(x).findViewById(R.id.product_v1_image);
                TextView productTitle = customizeGridProductLayout.getChildAt(x).findViewById(R.id.product_v1_title);
                TextView productDescription = customizeGridProductLayout.getChildAt(x).findViewById(R.id.product_v1_description);
                TextView productPrice = customizeGridProductLayout.getChildAt(x).findViewById(R.id.product_v1_price);

                Glide.with(itemView.getContext()).load(horizontalProductScrollModelList.get(x).getProductImage()).apply(new RequestOptions().placeholder(R.drawable.placeholder2)).into(productImage);
                productTitle.setText(horizontalProductScrollModelList.get(x).getProductTitle());
                productDescription.setText(horizontalProductScrollModelList.get(x).getProductDescription());
                productPrice.setText("Rs." + horizontalProductScrollModelList.get(x).getProductPrice() + "/-");

                if (!title.equals("")) {
                    int finalX = x;
                    customizeGridProductLayout.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Click on anything go to productVone class
                        Intent contactIntent = new Intent(v.getContext(), ProductsVone.class);
                        v.getContext().startActivity(contactIntent);
                        //Click on anything go to productVone class


                         /*  original code for grid products on home page
                        Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                        productDetailsIntent.putExtra("PRODUCT_ID",horizontalProductScrollModelList.get(finalX).getProductId());
                        itemView.getContext().startActivity(productDetailsIntent);

                        original code for grid products on home page    */
                    }
                });
            }
            }

            if (!title.equals("")) {

                customizeGridViewAllBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Click on anything go to productVone class
                        Intent contactIntent = new Intent(v.getContext(), ProductsVone.class);
                        v.getContext().startActivity(contactIntent);
                        //Click on anything go to productVone class

                    /*  original code for view all btn
                        ViewAllActivity.horizontalProductScrollModelList = horizontalProductScrollModelList;
                        Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                        viewAllIntent.putExtra("layout_code", 1);
                        viewAllIntent.putExtra("title", title);
                        itemView.getContext().startActivity(viewAllIntent);

                     original code for view all btn */

                    }
                });
            }

        }

    }

    public class BulkCardViewHolder extends RecyclerView.ViewHolder{

        private ImageView bulkImage1;
        private ConstraintLayout bulkBannerContainer;

        public BulkCardViewHolder(@NonNull View itemView) {
            super(itemView);

           bulkImage1 = itemView.findViewById(R.id.bulk_banner_imageView);
           bulkBannerContainer = itemView.findViewById(R.id.bulk_banner_container);

        }
        private void setBulkCardLayout(String resourceB, String colorB){

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent contactIntent = new Intent(v.getContext(), ProductsVone.class);
                    v.getContext().startActivity(contactIntent);
                }
            });

            Glide.with(itemView.getContext()).load(resourceB).apply(new RequestOptions().placeholder(R.drawable.placeholder1)).into(bulkImage1);
            bulkBannerContainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorB)));
           // bulkBannerContainer.setBackgroundColor(Integer.parseInt(colorB));
        }
    }


}
