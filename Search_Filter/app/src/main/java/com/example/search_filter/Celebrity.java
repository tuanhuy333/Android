package com.example.search_filter;

public class Celebrity {

    private String name; //ten
    private String nationality; //quoctich
    private int avatar; //anh dai dien

    public Celebrity(String name, String nationality, int avatar) {
        this.name = name;
        this.nationality = nationality;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
