package com.example.databinding_recyclerview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Observable;

public class Adapter_user extends RecyclerView.Adapter<Adapter_user.mViewHolder> {

    private List<User> mData;

    //contructor
    public Adapter_user(List<User> mData) {
        this.mData = mData;
    }

    
}
