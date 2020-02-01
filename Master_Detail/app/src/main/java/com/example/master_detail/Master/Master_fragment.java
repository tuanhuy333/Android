package com.example.master_detail.Master;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.master_detail.R;

import java.util.List;

public class Master_fragment extends Fragment implements LifecycleOwner, Adapter_master.onItemClickListener {

    private Adapter_master adapter_master;
    private MasterVM masterVM;

    public Master_fragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //tạo viewModel
        masterVM = ViewModelProviders.of(getActivity()).get(MasterVM.class);
        //quan sát sự thay đổi của List<String> mData
        masterVM.getmData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter_master.addData(strings);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.master_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.master_recyclerView);
        adapter_master = new Adapter_master();
        adapter_master.setmListener(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter_master);
    }

    @Override
    public void onClick(String result) {
        masterVM.setmSelectedItem(result);
        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
    }
}
