package com.example.mvp_recyclerview.Presenter;

import android.content.Context;

import com.example.mvp_recyclerview.Model.Database.DatabaseHelper;
import com.example.mvp_recyclerview.Model.Employee;
import com.example.mvp_recyclerview.getList_PresenterViewContract;

import java.util.ArrayList;
import java.util.List;

public class Presenter implements getList_PresenterViewContract.Presenter {

    //database
    DatabaseHelper db;

    Context context;
    //tang View
    getList_PresenterViewContract.View view;


    public Presenter(Context context,getList_PresenterViewContract.View view) {
        this.context=context;
        this.view = view;
    }

    @Override
    public void getList() {

        //List nhan tu database SQLite
        List<Employee> employeeList=new ArrayList<>();

        //khoi tao data
        //khi co database thi getDatabase()
        //employeeList = sqlite.getAllEmployee() ;
        db=new DatabaseHelper(context);
        employeeList=db.getAll();

        //Update View
        //View se show cai List nay
        view.showList(employeeList);

    }

    @Override
    public void addEmployee(String ten) {

        Employee employee=new Employee(ten);

        db=new DatabaseHelper(context);


        db.them(employee);

        view.showMessage("Added Employee");

    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee=new Employee(id);

        db=new DatabaseHelper(context);
        db.xoa_sp(employee);


        view.showMessage("Deleted Employee");

    }

    //delete , update ,find,...
    //...
    //...
    //...

}
