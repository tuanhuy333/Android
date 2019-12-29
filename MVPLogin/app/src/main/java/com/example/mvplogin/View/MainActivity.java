package com.example.mvplogin.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mvplogin.Presenter.SignInPresenter;
import com.example.mvplogin.R;
import com.example.mvplogin.SignInContract;

public class MainActivity extends AppCompatActivity implements SignInContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //day la tang View
        //co nhiem vu lay data tu nguoi dung nhap vao
        //vd: presenter.signIn(edtUser.getText(),edtPass.getText())

        SignInPresenter presenter=new SignInPresenter(this);
        presenter.signIn("huy","thanh");


    }


    @Override
    public void showSuccess(String message) {

        //dung message de truyen vao textView
        //neu day la List thi dung de update RecyclerView
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
