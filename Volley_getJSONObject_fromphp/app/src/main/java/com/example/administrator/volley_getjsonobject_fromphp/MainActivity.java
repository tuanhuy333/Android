package com.example.administrator.volley_getjsonobject_fromphp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String url="http://192.168.1.96:8080/my_code_php/get_database.php";


    ArrayList<user> userArrayList;
    adapter_user adapter_user;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        userArrayList=new ArrayList<>();
        adapter_user=new adapter_user(MainActivity.this,R.layout.row_user,userArrayList);

        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter_user);





        getDatabase_mySQL(url);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vie
            }
        });
    }

    public void getDatabase_mySQL(String url){
        RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        //sau khi lấy được mảng json từ database ...ta sẽ thêm vào List để đổ ra ListView
                        for(int i=0;i<response.length();++i){
                            try {
                                JSONObject jsonObject=response.getJSONObject(i);


                                userArrayList.add(new user(jsonObject.getString("username"),jsonObject.getString("password")));



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter_user.notifyDataSetChanged();
                        }



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
