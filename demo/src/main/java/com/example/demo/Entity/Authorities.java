package com.example.demo.Entity;

import jakarta.persistence.*;


@Entity
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;


    private String authority;
    @ManyToOne
    @JoinColumn(name = "user_authorities_id")
    private Users users;

    public Authorities() {

    }

    public Authorities(String authority, Users users) {
        this.authority = authority;
        this.users = users;
    }

    public Authorities(long id, String authority, Users users) {
        Id = id;
        this.authority = authority;
        this.users = users;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
