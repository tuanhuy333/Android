package com.example.administrator.app_note;

import java.io.Serializable;

public class note implements Serializable {

    private int id_note;
    private String title_note;
    private String content_note;

    public note() {
    }

    public note(int id_note, String title_note, String content_note) {
        this.id_note = id_note;
        this.title_note = title_note;
        this.content_note = content_note;
    }

    public int getId_note() {
        return id_note;
    }

    public void setId_note(int id_note) {
        this.id_note = id_note;
    }

    public String getTitle_note() {
        return title_note;
    }

    public void setTitle_note(String title_note) {
        this.title_note = title_note;
    }

    public String getContent_note() {
        return content_note;
    }

    public void setContent_note(String content_note) {
        this.content_note = content_note;
    }
}
