package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false, length = 130)
    private String username;
    @Column(nullable = false, length = 90)
    private String firsName;
    @Column(nullable = false, length = 90)
    private String lastName;
    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    @Pattern(message = "Password must be at least 8 characters long (max-40) and contain at least one upper-case English letter," +
              " one lower-case English letter, one digit, one special character" + " ", regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,40}$")
    @JsonIgnore
    private String password;

    @Column(nullable = false, unique = true)
    @Email(message = "Email is not valid")
    private String email;


    public Users() {

    }

    public Users(String username, String firsName, String lastName, String phoneNumber, String password, String email, Set<Role> role, Set<Authorities> authorities, Set<Product> products, Set<Orders> orderList) {
        this.username = username;
        this.firsName = firsName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.role = role;
        this.authorities = authorities;
        this.products = products;
        this.orderList = orderList;
    }

    public Set<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authorities> authorities) {
        this.authorities = authorities;
    }

    public Users(long id, String username, String firsName, String lastName, String phoneNumber, String password, String email, Set<Role> role, Set<Authorities> authorities, Set<Product> products, Set<Orders> orderList) {
        this.id = id;
        this.username = username;
        this.firsName = firsName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.role = role;
        this.authorities = authorities;
        this.products = products;
        this.orderList = orderList;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;



    @OneToMany(mappedBy = "users")
    private Set<Authorities> authorities;

    @OneToMany(mappedBy = "users")
    private Set<Product>products;

    @OneToMany(mappedBy = "users")
    private Set<Orders> orderList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Orders> getOrderList() {
        return orderList;
    }

    public void setOrderList(Set<Orders> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", first_name='" + firsName + '\'' +
                ", last_name='" + lastName+ '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
