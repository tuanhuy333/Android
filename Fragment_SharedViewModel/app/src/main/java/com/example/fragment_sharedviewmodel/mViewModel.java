package com.example.fragment_sharedviewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class mViewModel extends ViewModel {

    public MutableLiveData<String> message=new MutableLiveData<>();

    //set value de truyen di
    public void setMessage(String value){
        message.setValue(value);
    }
}
