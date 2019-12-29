package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edt_url;
    Button btn_go;
    WebView my_webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //
        edt_url=(EditText)findViewById(R.id.editText);
        btn_go=(Button)findViewById(R.id.button);
        my_webview=(WebView)findViewById(R.id.my_webview);

        //
        my_webview.setWebViewClient(new WebViewClient());
        //kich hoat javascript trong webview
        WebSettings webSettings=my_webview.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true);

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url=edt_url.getText().toString().trim();
                my_webview.loadUrl("http://"+url);
            }
        });


    }
}
