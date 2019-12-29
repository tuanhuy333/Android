package com.example.administrator.intent;

import java.io.Serializable;

public class hocsinh implements Serializable {
    private int id;
    private String ten;
    private double diem;

    public hocsinh(int id, String ten, double diem) {
        this.id = id;
        this.ten = ten;
        this.diem = diem;
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

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }
}
