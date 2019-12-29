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
public class A extends Fragment {


    public A() {
        // Required empty public constructor
    }
    TextView txta;
    EditText edta;
    Button btna;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_a, container, false);

        txta=(TextView)view.findViewById(R.id.txta);
        edta=(EditText)view.findViewById(R.id.edta);
        btna=(Button)view.findViewById(R.id.btna);

        btna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt_tieude=(TextView)getActivity().findViewById(R.id.textView);
                txt_tieude.setText(edta.getText().toString());
            }
        });


        return view;
    }

}
