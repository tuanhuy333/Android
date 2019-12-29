package com.example.chat_app_firebase.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {
    private String key_id;
    private String username;
    private String image_url;
    private String status;

    public User() {
    }

    public User(String key_id, String username, String image_url,String status) {
        this.key_id = key_id;
        this.username = username;
        this.image_url = image_url;
        this.status=status;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
