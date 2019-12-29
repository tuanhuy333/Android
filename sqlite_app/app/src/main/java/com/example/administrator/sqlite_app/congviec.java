package com.example.administrator.sqlite_app;

public class congviec {
    private int id_congviec;
    private String ten_congviec;

    public congviec(int id_congviec, String ten_congviec) {
        this.id_congviec = id_congviec;
        this.ten_congviec = ten_congviec;
    }

    public int getId_congviec() {
        return id_congviec;
    }

    public void setId_congviec(int id_congviec) {
        this.id_congviec = id_congviec;
    }

    public String getTen_congviec() {
        return ten_congviec;
    }

    public void setTen_congviec(String ten_congviec) {
        this.ten_congviec = ten_congviec;
    }
}
