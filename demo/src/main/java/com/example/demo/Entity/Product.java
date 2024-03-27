package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false,length = 50)
    private String productName;
    @Column(length = 20000)
    private String productDescription;
    private double discountPrice;
    @Column(nullable = false)
    private double actualPrice;
    @OneToOne
    private Orders order;
    @ManyToOne
    @JoinColumn(name = "user_id_product", nullable = false)
    private Users users;




    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Product() {

        }


    public Product(String productName, String productDescription, double discountPrice, double actualPrice, Orders order, Users users) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.discountPrice = discountPrice;
        this.actualPrice = actualPrice;
        this.order = order;
        this.users = users;
    }


    public Product(long id, String productName, String productDescription, double discountPrice, double actualPrice, Orders order, Users users) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.discountPrice = discountPrice;
        this.actualPrice = actualPrice;
        this.order = order;
        this.users = users;
    }

    public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductDescription() {
            return productDescription;
        }

        public void setProductDescription(String productDescription) {
            this.productDescription = productDescription;
        }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", discountPrice=" + discountPrice +
                ", actualPrice=" + actualPrice +
                ", order=" + order +
                ", users=" + users +
                '}';
    }
}
