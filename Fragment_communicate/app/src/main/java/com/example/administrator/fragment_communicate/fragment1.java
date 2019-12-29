package com.example.administrator.fragment_communicate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class fragment1 extends Fragment {
    TextView txtFrag1,txtActivity;
    Button btnFrag1;
    EditText edtFrag1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1, container,false);

        txtFrag1=(TextView)view.findViewById(R.id.txtFrag1);
        btnFrag1=(Button)view.findViewById(R.id.btnFrag1);
        edtFrag1=(EditText)view.findViewById(R.id.edtFrag1);

        btnFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtActivity=(TextView)getActivity().findViewById(R.id.txtMain); //truy cap den txtMain cua Activity
                txtActivity.setText(edtFrag1.getText().toString());
            }
        });
        return view;
    }
    public void change_text(String text){
        txtFrag1.setText(text);
    }
}
