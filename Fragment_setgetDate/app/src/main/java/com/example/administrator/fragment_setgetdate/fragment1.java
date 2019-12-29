package com.example.administrator.fragment_setgetdate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class fragment1 extends Fragment {
    TextView txtFrag;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1, container, false);
        //ánh xạ thành phần trong fragment
        txtFrag=(TextView)view.findViewById(R.id.txtFrag1);

        //lấy bundle
        Bundle bundle=new Bundle();
        bundle=getArguments();
        txtFrag.setText(bundle.getString("tenhs"));

        return view;
    }
}
