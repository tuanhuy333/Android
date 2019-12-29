package com.example.image_manager;

public class image {
    private int id;
    private String ten_image;
    private String url_image;

    public image(int id, String ten_image, String url_image) {
        this.id = id;
        this.ten_image = ten_image;
        this.url_image = url_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_image() {
        return ten_image;
    }

    public void setTen_image(String ten_image) {
        this.ten_image = ten_image;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}
