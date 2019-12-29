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

public class fragment2 extends Fragment {
    TextView txtFrag2,txtFragment1;
    Button btnFrag2;
    EditText edtFrag2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment2, container,false);
        txtFrag2=(TextView)view.findViewById(R.id.txtFrag2);
        btnFrag2=(Button)view.findViewById(R.id.btnFrag2);
        edtFrag2=(EditText)view.findViewById(R.id.edtFrag2);
        btnFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtFragment1=(TextView)getActivity().findViewById(R.id.txtFrag1);
                txtFragment1.setText(edtFrag2.getText().toString());
            }
        });
        return view;
    }
}
