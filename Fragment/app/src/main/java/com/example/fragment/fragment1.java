package com.example.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.administrator.fragment.R;

public class fragment1 extends Fragment implements View.OnClickListener{



    //gui du lieu tu frag 1 sang frag 2
    public interface OnSendMessageListener{
        void onSend(String msg);
    }

    private OnSendMessageListener onSendMessageListener;
    EditText edt_frag1;
    Button btn_frag1;

    public static fragment1 newInstance(){
        return new fragment1();
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        onSendMessageListener=(OnSendMessageListener)context;

    }

    @Override
    public void onClick(View v) {
        onSendMessageListener.onSend(edt_frag1.getText().toString().trim());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment1_layout,container,false);

        edt_frag1=(EditText)view.findViewById(R.id.edt_frag1);

        btn_frag1=(Button)view.findViewById(R.id.btn_frag1);
        btn_frag1.setOnClickListener(this);


        return view;
    }
}
