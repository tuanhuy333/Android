package com.example.administrator.giaotiep_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class B extends Fragment {
    TextView txtb;
    EditText edtb;
    Button btnb;

    public B() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_b, container, false);

        txtb=(TextView)view.findViewById(R.id.txtb);
        btnb=(Button)view.findViewById(R.id.btnb);
        edtb=(EditText)view.findViewById(R.id.edtb);

        btnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txta=getActivity().findViewById(R.id.txta);
                txta.setText(edtb.getText().toString());
            }
        });


        return view;
    }

}
