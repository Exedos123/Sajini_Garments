package com.exedos.sajinigarments;

public class CategoryItemModel {

    private String icon;
    private String categoryName;
    private String backgroundColor;

    public CategoryItemModel(String icon, String categoryName, String backgroundColor) {
        this.icon = icon;
        this.categoryName = categoryName;
        this.backgroundColor = backgroundColor;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    /*
    private int categoryImage;
    private String categoryTitle;
    private String backgroundColor;

    public CategoryItemModel(int categoryImage, String categoryTitle, String backgroundColor) {
        this.categoryImage = categoryImage;
        this.categoryTitle = categoryTitle;
        this.backgroundColor = backgroundColor;
    }

    public CategoryItemModel(String icon, String categoryName) {
    }

    public int getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }



   */
}
