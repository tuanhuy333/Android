package com.example.viewmodel.Livedata_ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuantityViewModel extends ViewModel {

    public int countAo = 0;
    public int countQuan = 0;

    private MutableLiveData<Integer> quantityAo = new MutableLiveData<>();
    private MutableLiveData<Integer> quantityQuan = new MutableLiveData<>();

    public QuantityViewModel() {
        quantityAo.setValue(0);
        quantityQuan.setValue(0);
    }


//    //get
    public MutableLiveData<Integer> get_quantityAo(){
        return quantityAo;
    }
    public MutableLiveData<Integer> get_quantityQuan(){
        return quantityQuan;
    }

    //tang so luong
    public void increaseAo(int quantity) {
        quantityAo.setValue(quantityAo.getValue() + quantity);
    }

    public void increaseQuan(int quantity) {
        quantityQuan.setValue(quantityQuan.getValue() + quantity);
    }

    //giam so luong
    public void decreaseAo(int quantity) {
        quantityAo.setValue(quantityAo.getValue() - quantity);
    }

    public void decreaseQuan(int quantity) {
        quantityQuan.setValue(quantityQuan.getValue() - quantity);
    }

}
