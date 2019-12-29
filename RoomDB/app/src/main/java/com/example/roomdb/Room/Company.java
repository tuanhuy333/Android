package com.example.roomdb.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Company")
public class Company {

    @PrimaryKey(autoGenerate = true)
    private int id_company;

    private String ten_company;

    public Company(int id_company, String ten_company) {
        this.id_company = id_company;
        this.ten_company = ten_company;
    }

    public int getId_company() {
        return id_company;
    }

    public void setId_company(int id_company) {
        this.id_company = id_company;
    }

    public String getTen_company() {
        return ten_company;
    }

    public void setTen_company(String ten_company) {
        this.ten_company = ten_company;
    }
}
