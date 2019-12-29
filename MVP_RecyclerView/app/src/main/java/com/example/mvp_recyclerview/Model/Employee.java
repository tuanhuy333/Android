package com.example.mvp_recyclerview.Model;

public class Employee {

    private int id;
    private String tenNhanVien;

    public Employee(int id, String tenNhanVien) {
        this.id = id;
        this.tenNhanVien = tenNhanVien;
    }

    public Employee(int id) {
        this.id = id;
    }

    public Employee(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }
}
