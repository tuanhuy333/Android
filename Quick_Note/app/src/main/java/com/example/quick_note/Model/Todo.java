package com.example.quick_note.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Todo implements Serializable {

    int id;
    String note;
    int status;
    String created_at;

    // constructors
    public Todo() {
    }

    public Todo(String note, int status) {
        this.note = note;
        this.status = status;
    }

    public Todo(int id, String note, int status) {
        this.id = id;
        this.note = note;
        this.status = status;
    }

    public Todo(int id,String note, int status, String created_at) {
        this.id=id;
        this.note = note;
        this.status = status;
        this.created_at = created_at;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCreatedAt(String created_at){
        this.created_at = created_at;
    }

    // getters
    public int getId() {
        return this.id;
    }

    public String getNote() {
        return this.note;
    }

    public int getStatus() {
        return this.status;
    }
    public String getCreateAt() {
        return this.created_at;
    }


}
