package com.exedos.sajinigarments;

public class WishListModel {
    private String productImage;
    private String productTitle;
    // private int freeCoupens;
    private String rating;
    private Long totalRatings;
    private String productPrice;
    private String cuttedPrice;
    private boolean COD;

    public WishListModel(String productImage, String productTitle, String rating, Long totalRatings, String productPrice, String cuttedPrice, boolean COD) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.rating = rating;
        this.totalRatings = totalRatings;
        this.productPrice = productPrice;
        this.cuttedPrice = cuttedPrice;
        this.COD = COD;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Long getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(Long totalRatings) {
        this.totalRatings = totalRatings;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCuttedPrice() {
        return cuttedPrice;
    }

    public void setCuttedPrice(String cuttedPrice) {
        this.cuttedPrice = cuttedPrice;
    }

    public boolean isCOD() {
        return COD;
    }

    public void setCOD(boolean COD) {
        this.COD = COD;
    }
}