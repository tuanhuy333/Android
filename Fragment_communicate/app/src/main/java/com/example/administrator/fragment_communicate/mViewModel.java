package com.example.administrator.fragment_communicate;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class mViewModel extends ViewModel {

    public MutableLiveData<String> message=new MutableLiveData<>();

    public void setMessage(String giatri){
        message.setValue(giatri);
    }
}
