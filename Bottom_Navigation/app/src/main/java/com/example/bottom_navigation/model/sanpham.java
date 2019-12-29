package com.example.bottom_navigation.model;

import java.io.Serializable;

public class sanpham implements Serializable {

    private int ma_sp;
    private String ten_sp;
    private int soluong_sp;
    private String donvi_tinh;
    private int gia_nhapvao;
    private int gia_ban;
    private byte[] hinh_sp;



    public sanpham(int ma_sp, String ten_sp,int gia_nhapvao, int gia_ban, int soluong_sp, String donvi_tinh, byte[] hinh_sp) {
        this.ma_sp = ma_sp;
        this.ten_sp = ten_sp;
        this.soluong_sp = soluong_sp;
        this.donvi_tinh = donvi_tinh;
        this.gia_nhapvao = gia_nhapvao;
        this.gia_ban = gia_ban;
        this.hinh_sp = hinh_sp;
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

    public int getSoluong_sp() {
        return soluong_sp;
    }

    public void setSoluong_sp(int soluong_sp) {
        this.soluong_sp = soluong_sp;
    }

    public String getDonvi_tinh() {
        return donvi_tinh;
    }

    public void setDonvi_tinh(String donvi_tinh) {
        this.donvi_tinh = donvi_tinh;
    }

    public int getGia_nhapvao() {
        return gia_nhapvao;
    }

    public void setGia_nhapvao(int gia_nhapvao) {
        this.gia_nhapvao = gia_nhapvao;
    }

    public int getGia_ban() {
        return gia_ban;
    }

    public void setGia_ban(int gia_ban) {
        this.gia_ban = gia_ban;
    }

    public byte[] getHinh_sp() {
        return hinh_sp;
    }

    public void setHinh_sp(byte[] hinh_sp) {
        this.hinh_sp = hinh_sp;
    }
}
