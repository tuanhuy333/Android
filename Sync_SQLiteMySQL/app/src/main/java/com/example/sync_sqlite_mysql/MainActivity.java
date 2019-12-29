package com.example.sync_sqlite_mysql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edt_name;
    Button btn_add;
    RecyclerView recyclerView;

    adapter_row_name adapter_row_name;
    List<Name>list;

    public static DBHelper db;
    public static String url="http://192.168.1.96:8080/sync_sqlite_mysql/insert.php";

    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        list=new ArrayList<>();

        db=new DBHelper(MainActivity.this);




        broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(isOnline()) {
                    final List<Name> list = MainActivity.db.getAll();
                    for (final Name item:list) {

                        //neu item nao chu dong bo
                        if (item.getStatus_sync() == 0) {

                            //thi insert len MySQL va update lai status_sync
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, MainActivity.url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                            //update lai status_sync
                                            db.update_status(item.getName(), item.getId());
                                           show_data();


                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                            ) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("name", item.getName());
                                    return params;
                                }
                            };

                            RequestQueue requestQueue = Volley.newRequestQueue(context);
                            requestQueue.add(stringRequest);


                        }
                    }

            }else{
                Toast.makeText(MainActivity.this, "Not connect avaible !", Toast.LENGTH_SHORT).show();
            }

        }};

        show_data();
        //them name
        btn_add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               String name=edt_name.getText().toString().trim();

               if(isOnline()){

                   saveInMySQL(name);

               }else{

                   saveInSQLite(name,0);

               }


           }
       });

    }
    public void saveInMySQL(final String name){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        saveInSQLite(name,1);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("name",name);
                return params;
            }
        };

        RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }
    public void saveInSQLite(String name,int status_sync){
        db.insert_name(name,status_sync);

        show_data();
    }
    private void initView() {

        edt_name=(EditText)findViewById(R.id.main_editName);
        btn_add=(Button)findViewById(R.id.main_btnAdd);
        recyclerView=(RecyclerView)findViewById(R.id.main_recyclerView);
    }
    public void show_data(){
        //lay data
        list.clear();
        list=db.getAll();


        //khoi tao adapter
        adapter_row_name adapterRowName=new adapter_row_name(MainActivity.this,R.layout.row_name,list);

        LinearLayoutManager linearLayout=new LinearLayoutManager(this);


        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(adapterRowName);
        adapterRowName.notifyDataSetChanged();
    }

    //kiem tra ket noi internet
    public boolean isOnline() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        //dang ky broadcastReceiver

        registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
}
