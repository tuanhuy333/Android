package com.example.master_detail.Detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.master_detail.Master.MasterVM;
import com.example.master_detail.R;

public class Detail_fragment extends Fragment implements LifecycleOwner {

    TextView txt_name;
    MasterVM masterVM;

    //tạo người đứng nhìn thay đổi data


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        masterVM= ViewModelProviders.of(getActivity()).get(MasterVM.class);
        masterVM.getmSelectedItem().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txt_name.setText(s);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_fragment, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //khi tạo được View rồi
        //thì ánh xạ
        txt_name = view.findViewById(R.id.txt_ten);
    }
}
