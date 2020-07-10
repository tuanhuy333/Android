package com.example.intentservice_broadcast_asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static  TextView txt;
    Button btn_start, btn_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ví dụ : "worker thread (intentService)" giao tiếp vs "main thread" thông qua "Broadcast"

        // initView
        initView();

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this,CountNumberIntentService.class));
            }
        });

    }

    private void initView() {
        txt = findViewById(R.id.textView);
        btn_start = findViewById(R.id.button);

    }

    // Asynctask
    public static class UpdateValueAsynctask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            return strings[0];

        }


        // thực hiện xong
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("a",s);
            // update UI
            txt.setText(s);
        }
    }
}
