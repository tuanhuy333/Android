package com.example.roomdb.Room;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Employee" , foreignKeys = @ForeignKey(entity = Company.class,
                                                            parentColumns = "id_company", //trong company
                                                            childColumns = "id_company",//trong employee
                                                            onDelete = ForeignKey.CASCADE))
//@Entity(indices = {@Index(value = {"first_name", "last_name"}, unique = true)}
//đánh dấu chỉ mục

//@E
public class Employee {
    @PrimaryKey(autoGenerate = true)
    public int employId;
    @ColumnInfo(name = "employ_name")
    public String name;
    public int id_company;

//    @Embedded (không muốn lưu company thành bảng riêng mà muốn chỉ có "trường" company thôi
//    public Company company;

    public Employee(int employId, String name, int id_company) {
        this.employId = employId;
        this.name = name;
        this.id_company = id_company;
    }
}