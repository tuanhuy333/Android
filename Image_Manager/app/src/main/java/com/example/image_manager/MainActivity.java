package com.example.image_manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    adapter_image adapter_image;
    List<image> list;
    String url_get="http://192.168.1.96:8080/image_manager/getAll.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //chuyen sang man hinh moi de them anh vao csdl
        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),add_image.class));
            }
        });





        hien_thi_data(url_get);


    }
    public void hien_thi_data(String url){
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        list=new ArrayList<image>();
                        for(int i=0;i<response.length();++i){
                            try {


                                JSONObject jsonObject=response.getJSONObject(i);
                                list.add(new image(jsonObject.getInt("id"),
                                        jsonObject.getString("ten_image"),
                                        jsonObject.getString("url_image")));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //   adapter_image.notifyDataSetChanged();
                        }
                        recyclerView=(RecyclerView)findViewById(R.id.my_recyclerView);
                        adapter_image = new adapter_image(getApplicationContext(), R.layout.card_image, list);
                        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                        recyclerView.setAdapter(adapter_image);

                        adapter_image.notifyDataSetChanged();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

                    }
                });

        requestQueue.add(jsonArrayRequest);

    }

}
