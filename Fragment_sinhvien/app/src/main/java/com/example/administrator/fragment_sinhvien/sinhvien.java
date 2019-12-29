package com.example.administrator.fragment_sinhvien;

public class sinhvien {

    private String ten;
    private double diemso;

    public sinhvien() {
    }

    public sinhvien(String ten, double diemso) {
        this.ten = ten;
        this.diemso = diemso;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getDiemso() {
        return diemso;
    }

    public void setDiemso(double diemso) {
        this.diemso = diemso;
    }
}
