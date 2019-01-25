package com.example.UserModule.Dto;

public class OrderHistoryDTO {

    private String orderId;
    private String productName;
    private String imageUrl;
    private String merchantName;
    private int quantity;
    private Double price;
    private String userId;
    private String productId;
    private String merchantId;
    private Double ratingGiven;
    private Boolean isRated;

    public OrderHistoryDTO() {
        this.ratingGiven = 0.0;
        this.isRated = false;
    }

    public Double getRatingGiven() {
        return ratingGiven;
    }

    public void setRatingGiven(Double ratingGiven) {
        this.ratingGiven = ratingGiven;
    }

    public Boolean getRated() {
        return isRated;
    }

    public void setRated(Boolean rated) {
        isRated = rated;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderHistoryDTO{" +
                "orderId='" + orderId + '\'' +
                ", productName='" + productName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", userId='" + userId + '\'' +
                ", productId='" + productId + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", ratingGiven=" + ratingGiven +
                ", isRated=" + isRated +
                '}';
    }
}
