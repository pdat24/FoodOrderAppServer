package com.project.food_order_app.user;

import com.project.food_order_app.cart.Cart;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {
    private String uid;
    private String email;
    private String password;
    private List<Cart> cart;

    public User() {
    }

    public User(String uid, String email, String password) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        cart = List.of();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }
}
