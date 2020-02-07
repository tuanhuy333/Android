package com.example.fragment_sharedviewmodel;

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

public class fragment_receiver extends Fragment {
    TextView txt_receiver;
    mViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //phai la .of(getActivity())
        mViewModel= ViewModelProviders.of(getActivity()).get(mViewModel.class);
        mViewModel.message.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txt_receiver.setText(s);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_receiver,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txt_receiver=view.findViewById(R.id.txt_receiver);

    }
}
