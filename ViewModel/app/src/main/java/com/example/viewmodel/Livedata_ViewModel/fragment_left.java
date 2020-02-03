package com.example.viewmodel.Livedata_ViewModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.viewmodel.R;

public class fragment_left extends Fragment {


    //khai bao view
    TextView txt_Ao,txt_Quan;
    Button btn_congAo,btn_truAo,btn_congQuan,btn_truQuan;

    //khai bao ViewModel
    QuantityViewModel quantityViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_left,container,false);

        initView(v);

        //chi dinh ViewModel
        quantityViewModel=new QuantityViewModel();
        quantityViewModel= ViewModelProviders.of(getActivity()).get(QuantityViewModel.class);



        //su kien click
        btn_congAo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityViewModel.get_quantityAo().setValue(quantityViewModel.get_quantityAo().getValue()+1);
                displayQuantity();

            }
        });
        btn_truAo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityViewModel.get_quantityAo().setValue(quantityViewModel.get_quantityAo().getValue()-1);
                displayQuantity();

            }
        });
        btn_congQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityViewModel.get_quantityQuan().setValue(quantityViewModel.get_quantityQuan().getValue()+1);
                displayQuantity();

            }
        });
        btn_truQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityViewModel.get_quantityQuan().setValue(quantityViewModel.get_quantityQuan().getValue()-1);
                displayQuantity();

            }
        });

        return v;
    }


    //khi da fragment duoc tao
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //bao toan data trong cac textView
        displayQuantity();
    }

    //display quantity
    private void displayQuantity(){

        txt_Ao.setText(quantityViewModel.get_quantityAo().getValue()+"");
        txt_Quan.setText(quantityViewModel.get_quantityQuan().getValue()+"");
    }


    private void initView(View v) {
        btn_congAo=(Button)v.findViewById(R.id.btncongAo);
        btn_truAo=(Button)v.findViewById(R.id.btntruAo);
        btn_congQuan=(Button)v.findViewById(R.id.btncongQuan);
        btn_truQuan=(Button)v.findViewById(R.id.btntruQuan);


        txt_Ao=(TextView)v.findViewById(R.id.countAo);
        txt_Quan=(TextView)v.findViewById(R.id.countQuan);
    }

}