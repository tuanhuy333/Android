package com.example.sync_sqlite_mysql;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        if(isOnline(context)){



            final List<Name> list=MainActivity.db.getAll();
            for(final Name item:list){

                //neu item nao chu dong bo
                if(item.getStatus_sync() == 0){

                    //thi insert len MySQL va update lai status_sync
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, MainActivity.url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    //update lai status_sync
                                    MainActivity.db.update_status(item.getName(),item.getId());

                                   context.sendBroadcast(new Intent());

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }

                    )
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>params=new HashMap<>();
                            params.put("name",item.getName());
                            return params;
                        }
                    };

                    RequestQueue requestQueue= Volley.newRequestQueue(context);
                    requestQueue.add(stringRequest);


                }
            }
        }else{
            Toast.makeText(context, "Not connect avaible !", Toast.LENGTH_SHORT).show();
        }

    }
    //kiem tra ket noi internet
    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
