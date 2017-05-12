package com.example.abdullah.inventoryapp;

import java.io.Serializable;

/**
 * Created by Abdullah on 5/11/2017.
 */

public class Product implements Serializable {

    private int id ;
    private String name;
    private int price;

    private int quantity;
    private byte[] img;

    public byte[] getImage() {
        return img;
    }

    public void setImage(byte[] img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product(){

    }
}
