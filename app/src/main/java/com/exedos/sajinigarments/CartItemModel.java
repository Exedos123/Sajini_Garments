 package com.exedos.sajinigarments;
public class CartItemModel {

    public static final int CART_ITEM = 0;
    public static final int TOTAL_AMOUNT = 1;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int productImage;
    private String productTitle;
    private String productPrice;
    private String cutPrice;
    private int productQuantity;
    private int offersApplied;


    public CartItemModel(int type, int productImage, String productTitle, String productPrice, String cutPrice, int productQuantity, int offersApplied) {
        this.type = type;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.cutPrice = cutPrice;
        this.productQuantity = productQuantity;
        this.offersApplied = offersApplied;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCutPrice() {
        return cutPrice;
    }

    public void setCutPrice(String cutPrice) {
        this.cutPrice = cutPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getOffersApplied() {
        return offersApplied;
    }

    public void setOffersApplied(int offersApplied) {
        this.offersApplied = offersApplied;
    }

    ////cart items
    /// cart total

    private String totalItems;
    private String totalItemprice;
    private String deliveryPrice;
    private String saveAmount;
    private String totalAmount;

    public CartItemModel(int type, String totalItems, String totalItemprice, String deliveryPrice,String totalAmount ,String saveAmount) {
        this.type = type;
        this.totalItems = totalItems;
        this.totalItemprice = totalItemprice;
        this.deliveryPrice = deliveryPrice;
        this.saveAmount = saveAmount;
        this.totalAmount = totalAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getTotalItemprice() {
        return totalItemprice;
    }

    public void setTotalItemprice(String totalItemprice) {
        this.totalItemprice = totalItemprice;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getSaveAmount() {
        return saveAmount;
    }

    public void setSaveAmount(String saveAmount) {
        this.saveAmount = saveAmount;
    }

    //// cart total
}
