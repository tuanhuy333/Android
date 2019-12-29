package com.example.callback;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment1 extends Fragment{
    EditText edt_frag1;

    private CallBack callBack;

    void setCallBack(CallBack callBack){
        this.callBack=callBack;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment1,container,false);

        edt_frag1=(EditText)v.findViewById(R.id.edt_frag1);


        sendData();

        return v;

    }

    private void sendData() {
        edt_frag1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s!=null){
                        callBack.onSend(s.toString());
                    }else {
                        callBack.onSend("");
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
