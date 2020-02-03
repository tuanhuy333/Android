package com.example.viewmodel.Livedata_ViewModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.viewmodel.R;

public class fragment_right extends Fragment {
    TextView txt_Ao, txt_Quan;

    //khai bao ViewModel de bao toan data
    QuantityViewModel quantityViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_right, container, false);

        initView(v);


        quantityViewModel = ViewModelProviders.of(getActivity()).get(QuantityViewModel.class);

        //them observer(nguoi quan sat su thay doi livedata)
        quantityViewModel.get_quantityAo().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                display();
            }
        });
        quantityViewModel.get_quantityQuan().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                display();
            }
        });

        return v;
    }

    public void display() {
        txt_Ao.setText(quantityViewModel.get_quantityAo().getValue() + "");
        txt_Quan.setText(quantityViewModel.get_quantityQuan().getValue() + "");
    }

    private void initView(View v) {

        txt_Ao = (TextView) v.findViewById(R.id.countAo_right);
        txt_Quan = (TextView) v.findViewById(R.id.countQuan_right);
    }
}