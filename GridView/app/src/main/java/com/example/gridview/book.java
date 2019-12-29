package com.example.gridview;

public class book {
    private String ten_sach;
    private int gia_sach;
    private int hinh_sach;

    public book(String ten_sach, int gia_sach, int hinh_sach) {
        this.ten_sach = ten_sach;
        this.gia_sach = gia_sach;
        this.hinh_sach = hinh_sach;
    }

    public String getTen_sach() {
        return ten_sach;
    }

    public void setTen_sach(String ten_sach) {
        this.ten_sach = ten_sach;
    }

    public int getGia_sach() {
        return gia_sach;
    }

    public void setGia_sach(int gia_sach) {
        this.gia_sach = gia_sach;
    }

    public int getHinh_sach() {
        return hinh_sach;
    }

    public void setHinh_sach(int hinh_sach) {
        this.hinh_sach = hinh_sach;
    }
}
