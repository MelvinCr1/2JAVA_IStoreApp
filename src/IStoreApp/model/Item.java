// Copyright (C) 2024 by CUREAU Melvin
// Released under the terms of the Creative Commons Licence
// --------------------

package IStoreApp.model;

public class Item {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String store;

    public Item(String name, double price, int quantity, String store) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    public String getStore() {
        return store;
    }
}