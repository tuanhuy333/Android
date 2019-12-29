package com.example.mvp_recyclerview.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp_recyclerview.Model.Employee;
import com.example.mvp_recyclerview.R;

import java.util.List;

public class Adapter_employee extends RecyclerView.Adapter {

    Context context;
    int layout;
    List<Employee> employeeList;

    public Adapter_employee(Context context, int layout, List<Employee> employeeList) {
        this.context = context;
        this.layout = layout;
        this.employeeList = employeeList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_id,txt_ten;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_id=itemView.findViewById(R.id.txt_id);
            txt_ten=itemView.findViewById(R.id.txt_ten);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Employee employee=employeeList.get(position);

        ((ViewHolder)holder).txt_id.setText(employee.getId()+"");
        ((ViewHolder)holder).txt_ten.setText(employee.getTenNhanVien());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    //lay employee
    public int getIdAt(int position){
        return  employeeList.get(position).getId();
    }
}
