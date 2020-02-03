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

public class fragment_top extends Fragment {
    TextView txt_tongcong;

    QuantityViewModel quantityViewModel;

    int tongcong=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_top,container,false);

       initView(v);

       quantityViewModel= ViewModelProviders.of(getActivity()).get(QuantityViewModel.class);


       //them observer (nguoi quan sat thay doi livedata)
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

    private void display(){

        int tongcong=quantityViewModel.get_quantityAo().getValue()+quantityViewModel.get_quantityQuan().getValue();

        txt_tongcong.setText(tongcong+"");
    }



    private void initView(View v) {
        txt_tongcong=(TextView)v.findViewById(R.id.txt_tongcong);

    }
}
