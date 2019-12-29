package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor proximity,conquay,vectorxoay;

    SensorEventListener sensorEventListener1,sensorEventListener2;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text);



        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        //vector xoay kết hợp dữ liệu thô được tạo bởi con quay hồi chuyển,gia tốc và từ kế để tạo ra 1 cảm biến
        vectorxoay=sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        conquay=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);






        //lay danh sách các Sensor được hỗ trợ trên thiết bị
        List<Sensor>sensorList=sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor data:sensorList){
            textView.append(data.getName()+" \n"+data.getVendor()+"\n"+data.getPower()+"\n");
        }

        //lắng nghe sensor proximity thay đổi tùy từng loại
        sensorEventListener1=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                //nếu giá trị là tương đương nhỏ hơn với khoảng cách tối đa của cảm biến
                if(event.values[0] < proximity.getMaximumRange()){

                    //thì có vật ở gần
                    getWindow().getDecorView().setBackgroundColor(Color.RED);

                }
                else {

                    //không có vật nào ở gần cảm biến
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                Toast.makeText(MainActivity.this, "light", Toast.LENGTH_LONG).show();
            }
        };

        //lắng nghe sensor gyrosco(con quay hồi chuyển) thay đổi tùy từng loại
        sensorEventListener2 =new SensorEventListener() {


            @Override
            public void onSensorChanged(SensorEvent event) {

                //trục x là event.values[0]
                //trục y là event.values[1]
                //trục z là event.values[2]

                if(event.values[2] > 0.5f){ //anticlockwise(ngược chiều kim đồng hồ)
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }
                else if(event.values[2] <0.5f){
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };



    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener1,proximity,sensorManager.SENSOR_DELAY_NORMAL);
        //sensorManager.registerListener(sensorEventListener2,conquay,sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener1);
        //sensorManager.unregisterListener(sensorEventListener2);
    }
}
