package com.example.locationmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);

        boolean Is_gps=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean Is_network=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean Is_passvive=locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);

        Log.d("a",Is_gps+"gps");
        Log.d("a",Is_network+"net");
        Log.d("a",Is_passvive+"");
        Log.d("a",getApplication().getPackageName()+"jij");
        TextView textView=findViewById(R.id.text);
        textView.setText(getApplication().getPackageName());


        Intent sender = new Intent()
                .setAction(Intent.ACTION_VIEW)
                .setData(Uri.parse("com.android.workmanager"));
        //Intent.createChooser(sender, "Select app to edit the file");
        startActivity(sender);

    }
}
