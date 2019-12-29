package com.example.administrator.fragment_sinhvien;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class fragmentList_sv extends ListFragment {
    ArrayList<sinhvien> arr_sv;
    sinhvien_adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        arr_sv=new ArrayList<>();
        add_sv();
        adapter=new sinhvien_adapter(getActivity(),R.layout.row_sv,arr_sv);
        setListAdapter(adapter);
        return inflater.inflate(R.layout.fragment_sv,container,false);


    }
    public void add_sv(){
        arr_sv.add(new sinhvien("Tuấn Huy",8));
        arr_sv.add(new sinhvien("Hoàng Phi",6));
        arr_sv.add(new sinhvien("Trọng Đạt",7));
        arr_sv.add(new sinhvien("Văn Tuyên",8.5));
    }
}
