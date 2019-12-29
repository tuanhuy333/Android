package com.example.recyclerview_itemclick;

public class item {
    private int id_item;
    private String ten_item;

    public item(int id_item, String ten_item) {
        this.id_item = id_item;
        this.ten_item = ten_item;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public String getTen_item() {
        return ten_item;
    }

    public void setTen_item(String ten_item) {
        this.ten_item = ten_item;
    }
}
