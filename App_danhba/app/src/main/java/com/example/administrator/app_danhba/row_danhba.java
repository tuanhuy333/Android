package com.example.administrator.app_danhba;

public class row_danhba {

    private int ma_dongdanhba;
    private String ten_lienlac;
    private String sdt_lienlac;
    private byte[] hinh_lienlac;

    public row_danhba(int ma_dongdanhba, String ten_lienlac, String sdt_lienlac, byte[] hinh_lienlac) {
        this.ma_dongdanhba = ma_dongdanhba;
        this.ten_lienlac = ten_lienlac;
        this.sdt_lienlac = sdt_lienlac;
        this.hinh_lienlac = hinh_lienlac;
    }

    public byte[] getHinh_lienlac() {
        return hinh_lienlac;
    }

    public void setHinh_lienlac(byte[] hinh_lienlac) {
        this.hinh_lienlac = hinh_lienlac;
    }

    public int getMa_dongdanhba() {
        return ma_dongdanhba;
    }

    public void setMa_dongdanhba(int ma_dongdanhba) {
        this.ma_dongdanhba = ma_dongdanhba;
    }

    public String getTen_lienlac() {
        return ten_lienlac;
    }

    public void setTen_lienlac(String ten_lienlac) {
        this.ten_lienlac = ten_lienlac;
    }

    public String getSdt_lienlac() {
        return sdt_lienlac;
    }

    public void setSdt_lienlac(String sdt_lienlac) {
        this.sdt_lienlac = sdt_lienlac;
    }


}
