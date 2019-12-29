package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment2 extends Fragment {
    TextView txt_frag2;
    public static fragment2 newInstance(String msg){
        fragment2 fragment2=new fragment2();

        Bundle args=new Bundle();
        args.putString("key",msg);
        fragment2.setArguments(args);
        return fragment2;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment2_layout,container,false);

        txt_frag2=(TextView)view.findViewById(R.id.textView);
        initData();

        return view;
    }
    private void initData(){
        if(getArguments() != null){
            String msg=getArguments().getString("key");
            txt_frag2.setText(msg);
        }
    }
}