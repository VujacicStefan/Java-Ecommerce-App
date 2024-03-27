package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;


    private String orderName;
    @Column(nullable = false)
    private String address;
    private String order_contact_number;  //other phone to use
    private String orderStatus;  //write if you send order
    @OneToOne
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;
    @Column(nullable = false)
    private int orderItems;

    private double orderWeight;


    public Orders() {
    }

    public Orders(String orderName, String address, String order_contact_number, String orderStatus, Product product, Users users, int orderItems, double orderWeight) {
        this.orderName = orderName;
        this.address = address;
        this.order_contact_number = order_contact_number;
        this.orderStatus = orderStatus;
        this.product = product;
        this.users = users;
        this.orderItems = orderItems;
        this.orderWeight = orderWeight;
    }

    public Orders(long orderId, String orderName, String address, String order_contact_number, String orderStatus, Product product, Users users, int orderItems, double orderWeight) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.address = address;
        this.order_contact_number = order_contact_number;
        this.orderStatus = orderStatus;
        this.product = product;
        this.users = users;
        this.orderItems = orderItems;
        this.orderWeight = orderWeight;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrder_contact_number() {
        return order_contact_number;
    }

    public void setOrder_contact_number(String order_contact_number) {
        this.order_contact_number = order_contact_number;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public int getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(int orderItems) {
        this.orderItems = orderItems;
    }

    public double getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(double orderWeight) {
        this.orderWeight = orderWeight;
    }


    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", address='" + address + '\'' +
                ", order_contact_number='" + order_contact_number + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", product=" + product +
                ", users=" + users +
                ", orderItems=" + orderItems +
                ", orderWeight=" + orderWeight +
                '}';
    }
}

