package com.example.viewpaper.Model;

public class hocsinh  {
    private int id;
    private String ten;
    private String lop;

    public hocsinh() {
    }

    public hocsinh(int id, String ten, String lop) {
        this.id = id;
        this.ten = ten;
        this.lop = lop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}
