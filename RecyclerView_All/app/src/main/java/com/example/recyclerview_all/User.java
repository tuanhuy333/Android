package com.example.recyclerview_all;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int img;
    private String email;
    private String password;

    public User(int img, String email, String password) {
        this.img = img;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    //tao du lieu
    public static List<User> mData(int soluong) {
        List<User> list = new ArrayList<>();
        for (int i = 1; i < soluong; ++i) {
            list.add(new User(R.drawable.ic_launcher_background, "hu" + i + "@gmail.com", "tuikhongbiet" + i));
        }
        return list;
    }
}
