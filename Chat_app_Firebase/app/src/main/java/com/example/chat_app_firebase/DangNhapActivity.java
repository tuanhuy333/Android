package com.example.chat_app_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chat_app_firebase.Activity.DangKyActivity;
import com.example.chat_app_firebase.Activity.TaskAcitivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DangNhapActivity extends AppCompatActivity {

    EditText edt_email,edt_password;
    Button btn_dangnhap,btn_dangky;

    //khai bao Firebase
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ view
        initView();

        //khoi tao Firebase
        mAuth=FirebaseAuth.getInstance();


        //click dang nhap
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=edt_email.getText().toString().trim();
                String password=edt_password.getText().toString().trim();

                if(!email.isEmpty() && !password.isEmpty()){
                    dangnhap(email,password);
                }
                else {
                    Toast.makeText(DangNhapActivity.this, "Fill out , please !", Toast.LENGTH_SHORT).show();
                }



            }
        });


        //click dang ky
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
            }
        });
    }

    private void initView() {
        edt_email=(EditText)findViewById(R.id.editText);
        edt_password=(EditText)findViewById(R.id.editText2);
        btn_dangnhap=(Button)findViewById(R.id.button);
        btn_dangky=(Button)findViewById(R.id.button2);
    }

    public void dangnhap(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            //FirebaseUser user = mAuth.getCurrentUser();


                            Toast.makeText(DangNhapActivity.this,"Log in is successed", Toast.LENGTH_SHORT).show();
                            startActivity(  new Intent(DangNhapActivity.this, TaskAcitivity.class));
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(DangNhapActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


}
