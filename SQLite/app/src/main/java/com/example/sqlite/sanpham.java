package com.example.sqlite;

public class sanpham {
    private int ma_sp;
    private String ten_sp;
    private int gia_sp;
    private int soluong_sp;

    public sanpham(int ma_sp,String ten_sp, int gia_sp, int soluong_sp) {
        this.ma_sp=ma_sp;
        this.ten_sp = ten_sp;
        this.gia_sp = gia_sp;
        this.soluong_sp = soluong_sp;
    }
    public sanpham(String ten_sp, int gia_sp, int soluong_sp) {
        this.ten_sp = ten_sp;
        this.gia_sp = gia_sp;
        this.soluong_sp = soluong_sp;
    }

    public int getMa_sp() {
        return ma_sp;
    }

    public void setMa_sp(int ma_sp) {
        this.ma_sp = ma_sp;
    }

    public String getTen_sp() {
        return ten_sp;
    }

    public void setTen_sp(String ten_sp) {
        this.ten_sp = ten_sp;
    }

    public int getGia_sp() {
        return gia_sp;
    }

    public void setGia_sp(int gia_sp) {
        this.gia_sp = gia_sp;
    }

    public int getSoluong_sp() {
        return soluong_sp;
    }

    public void setSoluong_sp(int soluong_sp) {
        this.soluong_sp = soluong_sp;
    }
}
