package com.example.fragment_sharedviewmodel;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class fragment_sender extends Fragment {
    Button btn;
    EditText edt;

    mViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //khoi tao viewModel
        //phai la .of(getActivity()) de viewModel nhan biet Activity dang chua fragment
        mViewModel= ViewModelProviders.of(getActivity()).get(mViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sender,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn=view.findViewById(R.id.btn_sender);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.setMessage("hello world ! i will win");
            }
        });

        edt=view.findViewById(R.id.edt);
        //thay doi khi nhap tung ky tu
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.setMessage(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
