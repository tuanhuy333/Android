package com.example.master_detail.Master;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MasterVM extends AndroidViewModel {
    private MutableLiveData<List<String>> mData = new MutableLiveData<>();
    private MutableLiveData<String> mSelectedItem = new MutableLiveData<>();

    public MasterVM(@NonNull Application application) {
        super(application);
        mData.setValue(createData()); //khởi tạo data
    }

    //getter
    public MutableLiveData<List<String>> getmData() {
        return mData;
    }

    public MutableLiveData<String> getmSelectedItem() {
        return mSelectedItem;
    }

    //set value
    public void setmSelectedItem(String selectedItem) {
        mSelectedItem.setValue(selectedItem);
    }

    //tạo data
    private List<String> createData() {
        List<String> data = new ArrayList<>();

        data.add("Tuấn Huy");
        data.add("Tuấn Kiệt");
        data.add("Tuấn Anh");
        data.add("Trung Tín");
        data.add("Mỹ Thanh");
        data.add("Yến Linh");
        data.add("Đức Hiền");

        return data;
    }
}
