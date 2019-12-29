package com.example.administrator.recyclerview;

public class hocsinh {
    private int ma_hocsinh;
    private String ten_hocsinh;
    private String lop_hocsinh;

    public hocsinh(int ma_hocsinh, String ten_hocsinh, String lop_hocsinh) {
        this.ma_hocsinh = ma_hocsinh;
        this.ten_hocsinh = ten_hocsinh;
        this.lop_hocsinh = lop_hocsinh;
    }

    public int getMa_hocsinh() {
        return ma_hocsinh;
    }

    public void setMa_hocsinh(int ma_hocsinh) {
        this.ma_hocsinh = ma_hocsinh;
    }

    public String getTen_hocsinh() {
        return ten_hocsinh;
    }

    public void setTen_hocsinh(String ten_hocsinh) {
        this.ten_hocsinh = ten_hocsinh;
    }

    public String getLop_hocsinh() {
        return lop_hocsinh;
    }

    public void setLop_hocsinh(String lop_hocsinh) {
        this.lop_hocsinh = lop_hocsinh;
    }
}
