package com.example.callback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment2 extends Fragment implements CallBack{
    TextView txt_frag2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment2,container,false);
        txt_frag2=(TextView)v.findViewById(R.id.txt_frag2);

        return v;

    }

    @Override
    public void onSend(String text) {
        txt_frag2.setText("sad "+text);
    }
}
