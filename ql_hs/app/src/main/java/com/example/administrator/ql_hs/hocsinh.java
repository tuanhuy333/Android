package com.example.administrator.ql_hs;

import java.io.Serializable;

public class hocsinh implements Serializable {

    private int id;
    private String ten;
    private String lop;
    private float diem;

    public hocsinh(int id, String ten, String lop, float diem) {
        this.id = id;
        this.ten = ten;
        this.lop = lop;
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

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }
}
