package com.example.customlistview;

public class sanpham {
    private int id;
    private String ten_sp;

    public sanpham(int id, String ten_sp) {
        this.id = id;
        this.ten_sp = ten_sp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_sp() {
        return ten_sp;
    }

    public void setTen_sp(String ten_sp) {
        this.ten_sp = ten_sp;
    }
}
