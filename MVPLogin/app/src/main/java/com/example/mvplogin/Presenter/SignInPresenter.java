package com.example.mvplogin.Presenter;

import com.example.mvplogin.Model.User;
import com.example.mvplogin.SignInContract;

public class SignInPresenter implements SignInContract.Presenter {

    //giao tiep voi View
    SignInContract.View signInView;

    //contructor
    public SignInPresenter(SignInContract.View signInView) {
        this.signInView = signInView;
    }

    @Override
    public void signIn(String name, String password) {

        //lam viec voi model
        User u=new User(name,password);

        if(u.getUsername().equals("huy") && u.getPassword().equals("thanh")){
            signInView.showSuccess("thanh cong");
        }
        else {
            signInView.showError("that bai");
        }


    }
}
