package edu.cseju.applicationtechshopju.model;

import java.io.Serializable;

public class Product implements Serializable {

    public String productId, productName, productPrice, imageLink;

    public Product() {

    }

    public Product(String productId, String productName, String productPrice, String imageLink) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.imageLink = imageLink;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
