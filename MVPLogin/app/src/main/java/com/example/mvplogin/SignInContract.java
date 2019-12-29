package com.example.mvplogin;

public interface SignInContract {

    interface View{
        void showSuccess(String message);
        void showError(String message);

        //void showProcessBar(int i);

        //update UI
        //..
        //..
        //..
        //..
    }
    interface Presenter{
        void signIn(String name,String password);

        //void addProduct(Product product)
        //void deleteProduct(Product product)


        //them xoa sua csdl,tim kiem , ...
        //..
        //..
        //..
        //..


    }
}
