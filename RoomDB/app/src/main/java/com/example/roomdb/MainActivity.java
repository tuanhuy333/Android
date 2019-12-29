package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.example.roomdb.Room.AppDatabase;
import com.example.roomdb.Room.Company;
import com.example.roomdb.Room.Employee;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    TextView textView;
    List<Employee>employeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text);


        appDatabase=AppDatabase.getInstance(getApplication());


//        appDatabase.employDao().insertCompany(new Company(1,"VNPT"));
//        appDatabase.employDao().insertCompany(new Company(2,"FPT"));
//        appDatabase.employDao().insertCompany(new Company(3,"TMA"));
//
//        appDatabase.employDao().insertEmploy(new Employee(1,"Huy",3));
//        appDatabase.employDao().insertEmploy(new Employee(2,"Quan",1));
//        appDatabase.employDao().insertEmploy(new Employee(3,"Hau",2));
//        appDatabase.employDao().insertEmploy(new Employee(4,"Thien",2));
//        appDatabase.employDao().insertEmploy(new Employee(5,"Kiet",3));
        employeeList=new ArrayList<>();
//
        employeeList=appDatabase.employDao().getEmployeeByCompany(2);
        Toast.makeText(this, employeeList.get(1).name, Toast.LENGTH_SHORT).show();







    }
}
