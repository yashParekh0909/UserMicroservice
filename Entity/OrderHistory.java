package com.example.UserModule.Entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = OrderHistory.TABLE_NAME)
public class OrderHistory {

    public static final String TABLE_NAME="OrderHistory";

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    private String orderId;

    private String productName;
    private String imageUrl;
    private String merchantName;
    private int quantity;
    private Double price;
    private Double ratingGiven;
    private Boolean isRated;

    private String productId;

    private String merchantId;

    public OrderHistory() {
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModule userModule;

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

    public UserModule getUserModule() {
        return userModule;
    }

    public void setUserModule(UserModule userModule) {
        this.userModule = userModule;
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
        return "OrderHistory{" +
                "orderId='" + orderId + '\'' +
                ", productName='" + productName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", userModule=" + userModule +
                '}';
    }
}
