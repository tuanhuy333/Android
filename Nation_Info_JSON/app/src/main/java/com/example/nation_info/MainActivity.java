package com.example.nation_info;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

// java.net.SocketException: socket failed: EPERM (Operation not permitted)
// uninstall app cài lại

//nếu bị lỗi clearTextHttp trafic thì thêm useClearTextTrafic="true" trong AndroidManifest.xml
//vì android >9 thì clearText support mặc định sẽ bị tắt
//lỗi này đối với những trang http

public class MainActivity extends AppCompatActivity {

    String baseURL = "http://api.geonames.org/countryInfoJSON?&username=aporter&style=full";

    //
    ListView listView;

    //
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.mListView);


        //
        if (isNetworkAvailable(this)) {
            Asynctask_getJSON asynctask_getJSON = new Asynctask_getJSON();
            asynctask_getJSON.execute(baseURL);
        } else {
            Toast.makeText(this, "Thiết bị của bạn chưa có kết internet !", Toast.LENGTH_SHORT).show();
        }




    }

    //check internet
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


    // ==============asynctask=========
    public class Asynctask_getJSON extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        //
        String json = "";
        JSONObject jsonObject = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        //androidHttpClient giống như webbrowser để gửi request cho server
        // AndroidHttpClient androidHttpClient=AndroidHttpClient.newInstance("");
        //android 5 ko còn dùng

        @Override
        protected String doInBackground(String... strings) {
            //get data from url
            try {


                URL url = new URL(strings[0]); //sử dụng URL của java để truy cập intenet
                HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                if (httpsURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpsURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }
                    json = stringBuilder.toString().trim();


                    Log.d("a", json);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.d("a", e.toString() + "sa");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("a", e.toString() + "2");
            }


            return json; //trả về string json cho hàm onPostExecute
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

            //parse data string to JSON
            try {
                jsonObject = new JSONObject(s);

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("a", e.toString() + "3");
            }

            //chuyển json Object sang Json Array để lấy dữ liệu
            try {


                final JSONArray jsonArray = jsonObject.getJSONArray("geonames");

                //JSONObject object = jsonArray.getJSONObject(2);

                //thêm data vào mảng
                arrayList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); ++i) {
                    arrayList.add(jsonArray.getJSONObject(i).getString("countryName"));
                }


                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);

                listView.setAdapter(arrayAdapter);

                //click item list view sẽ sang Activity khác và hiện thông tin
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        try {
                            String tenquocgia = arrayList.get(position);
                            String danso = jsonArray.getJSONObject(position).getString("population");
                            String dientich = jsonArray.getJSONObject(position).getString("areaInSqKm");
                            String maquocgia = jsonArray.getJSONObject(position).getString("countryCode");

                            //truyền thông qua bundle
                            Bundle b = new Bundle();
                            b.putString("tenquocgia", tenquocgia);
                            b.putString("danso", danso);
                            b.putString("dientich", dientich);
                            b.putString("maquocgia", maquocgia);

                            //chuyển sang activity 2 để show
                            Intent i = new Intent(MainActivity.this, Main2Activity.class);
                            i.putExtra("dataCountry", b);
                            startActivity(i);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

                //  Log.d("a1", object.getString("continent"));
                // Toast.makeText(context, object.getString("name"), Toast.LENGTH_SHORT).show();


            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("a1", e.toString());

            }

        }
    }
}
