package edu.cseju.applicationtechshopju.model;

public class Order {

    public String orderID, productID;
    public String customerName, customerPhone, customerAddress, bkashID;


    public Order(String orderID, String productID, String customerName, String customerPhone, String customerAddress, String bkashID) {
        this.orderID = orderID;
        this.productID = productID;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.bkashID = bkashID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", productID='" + productID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", bkashID='" + bkashID + '\'' +
                '}';
    }
}
