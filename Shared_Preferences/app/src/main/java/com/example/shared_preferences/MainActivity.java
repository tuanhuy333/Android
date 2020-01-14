package com.example.shared_preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //khai báo biến
    EditText edt_username, edt_password;
    CheckBox checkBox;
    Button btn_login;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();


        sharedPreferences = getSharedPreferences("data_login", MODE_PRIVATE);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_username.getText().toString().equals("mythanh") &&
                        edt_password.getText().toString().equals("1234")) {

                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                    //nếu đăng nhập thành công thì lưu thông tin đăng nhập
                    //thì checkbox được check
                    if (checkBox.isChecked()) {

                        //ghi nội dung
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("username", edt_username.getText().toString());
                        editor.putString("password", edt_password.getText().toString());
                        editor.putBoolean("checked", true);

                        editor.commit();//xác nhận

                    } else {
                        //không nhớ
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("username");
                        editor.remove("password");
                        editor.remove("checked");
                        editor.commit();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                }
            }
        });
        //lấy giá trị shared prefrenences
        //(sẽ hiển thị cho lần đăng nhập sau)

        edt_username.setText(sharedPreferences.getString("username", ""));
        edt_password.setText(sharedPreferences.getString("password", ""));
        checkBox.setChecked(sharedPreferences.getBoolean("checked", false));

    }


    public void anhxa() {

        edt_username = (EditText) findViewById(R.id.editText1);
        edt_password = (EditText) findViewById(R.id.editText2);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        btn_login = (Button) findViewById(R.id.button);


    }


}
