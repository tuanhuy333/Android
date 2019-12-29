package com.example.viewpaper.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpaper.Adapter.Adapter_hocsinh;
import com.example.viewpaper.MainActivity;
import com.example.viewpaper.Model.hocsinh;
import com.example.viewpaper.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class fragment extends Fragment {

    TextView textView;


    RecyclerView recyclerView;
    Adapter_hocsinh adapter_hocsinh;
    List<hocsinh>list;

    public static fragment newInstance(String data){
        fragment fragment=new fragment();

        Bundle args=new Bundle();
        args.putString("data",data);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment,container,false);

       initView(view);

        //lấy data truyền vào từ khi khởi tạo Fragment (Ở MainActivity)

       Bundle bundle=getArguments();
       String txt=bundle.getString("data");

       textView.setText("This is Fragment "+ txt);





        //data recyclerView
        list=new ArrayList<>();
        list.add(new hocsinh(1,"Tuấn Huy","9a3"));
        list.add(new hocsinh(2,"Mỹ Thanh","9a8"));

        adapter_hocsinh = new Adapter_hocsinh(getContext(),R.layout.row_hocsinh,list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter_hocsinh);
        recyclerView.setLayoutManager(linearLayoutManager);






        return view;
    }
    public void initView(View view){
        textView=(view).findViewById(R.id.text_fragment);
        recyclerView=(view).findViewById(R.id.recyclerView);

    }
}
