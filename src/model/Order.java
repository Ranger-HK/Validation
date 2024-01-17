package model;

import java.util.ArrayList;

/**
 * @Created By Ravindu Prathibha
 * @created 1/16/2024 - 3:58 PM
 * @project Thogakade
 */
public class Order {
    private String orderId;
    private String customerId;
    private String orderDate;
    private String orderTime;
    private double cost;
    private ArrayList<ItemDetails> itemDetails;

    public Order() {
    }

    public Order(String orderId, String customerId, String orderDate, String orderTime, double cost, ArrayList<ItemDetails> itemDetails) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.cost = cost;
        this.itemDetails = itemDetails;
    }

    public String getOrderID() {
        return orderId;
    }

    public void setOrderID(String orderID) {
        this.orderId = orderID;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<ItemDetails> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ArrayList<ItemDetails> itemDetails) {
        this.itemDetails = itemDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", cost=" + cost +
                ", itemDetails=" + itemDetails +
                '}';
    }
}
