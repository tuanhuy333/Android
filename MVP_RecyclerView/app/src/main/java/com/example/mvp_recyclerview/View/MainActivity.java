package com.example.mvp_recyclerview.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mvp_recyclerview.Model.Employee;
import com.example.mvp_recyclerview.Presenter.Presenter;
import com.example.mvp_recyclerview.R;
import com.example.mvp_recyclerview.getList_PresenterViewContract;

import java.util.List;

public class MainActivity extends AppCompatActivity implements getList_PresenterViewContract.View{
    RecyclerView recyclerView;
    Adapter_employee adapter_employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);


        //phai goi presenter de thuc hien hanh dong cua minh
        final Presenter presenter=new Presenter(MainActivity.this,this);

        //presenter.addEmployee(edt1.getText(),edt2.getText())
        presenter.addEmployee("Thanh");

        presenter.getList();

        //swipe recycler view for delete Employee
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|
                                                                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //viewHolder.getAdapterPosition() chi la vi tri theo thu tu trong List thoi

//                Employee employee=adapter_employee.getAt(viewHolder.getAdapterPosition());
//                int id=employee.getId();

                int id=adapter_employee.getIdAt(viewHolder.getAdapterPosition());

                presenter.deleteEmployee(id);




            }
        }).attachToRecyclerView(recyclerView);

    }



    @Override
    public void showList(List<Employee> employeeList) {
        //truyen vao adapter (context,layout,list)
        adapter_employee=new Adapter_employee(MainActivity.this,R.layout.employee_item,employeeList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_employee);
        adapter_employee.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
