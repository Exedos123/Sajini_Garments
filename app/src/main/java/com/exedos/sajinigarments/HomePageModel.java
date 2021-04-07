package com.exedos.sajinigarments;

import java.util.List;

public class HomePageModel {

    public static final int BANNER_SLIDER = 0;
    public static final int HORIZONTAL_TRENDING = 1;
    public static final int CUSTOMIZE_GRIDLAYOUT = 2;
    public static final int BULK_CARD = 3;


    private int type;


    ////////////// Banner Slider
    private List<SliderModel> sliderModelList;

    public HomePageModel(int type, List<SliderModel> sliderModelList) {
        this.type = type;
        this.sliderModelList = sliderModelList;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }

    public void setSliderModelList(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }
    ////////////// Banner Slider



    private String title;
    private String backgroundColor;
    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;


    //////Horizontal trending layout ViewAllProducts
    private List<WishListModel> viewAllProductList;

    public HomePageModel(int type, String title, String backgroundColor, List<HorizontalProductScrollModel> horizontalProductScrollModelList, List<WishListModel> viewAllProductList) {
        this.type = type;
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
        this.viewAllProductList = viewAllProductList;
    }

    public List<WishListModel> getViewAllProductList() {
        return viewAllProductList;
    }

    public void setViewAllProductList(List<WishListModel> viewAllProductList) {
        this.viewAllProductList = viewAllProductList;
    }
    //////Horizontal trending layout ViewAll Products



    //////Horizontal trending layout  &&   Customize Grid layout

    public HomePageModel(int type, String title, String backgroundColor, List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.type = type;
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }




    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HorizontalProductScrollModel> getHorizontalProductScrollModelList() {
        return horizontalProductScrollModelList;
    }

    public void setHorizontalProductScrollModelList(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    //////Horizontal trending layout  &&   Customize Grid layout



    ////////////// BULK Card

    private String bulkImage;
    private String backGroundColor;

    public HomePageModel(int type, String bulkImage, String backGroundColor) {
        this.type = type;
        this.bulkImage = bulkImage;
        this.backGroundColor = backGroundColor;
    }

    public String getBulkImage() {
        return bulkImage;
    }

    public void setBulkImage(String bulkImage) {
        this.bulkImage = bulkImage;
    }

    public String getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(String backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    ////////////// BULK Card


}
