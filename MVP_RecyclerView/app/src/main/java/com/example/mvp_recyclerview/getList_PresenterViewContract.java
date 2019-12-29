package com.example.mvp_recyclerview;

import com.example.mvp_recyclerview.Model.Employee;

import java.util.List;

public interface getList_PresenterViewContract {

    interface Presenter{

        void getList();

        void addEmployee(String ten);

        void deleteEmployee(int id);

        //update , find ,....handle
    }
    interface View{
        void showList(List<Employee> employeeList);

        void showMessage(String message);

        //show process bar...
    }

}
