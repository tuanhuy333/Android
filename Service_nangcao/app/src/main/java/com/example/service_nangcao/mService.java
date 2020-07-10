package com.example.service_nangcao;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class mService extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public mService() {
        super("My intent service");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("a","service is started ");
        //thực hiện download file từ internet
        try {
           int result=DownloadFile(new URL("http://www.amazon.com/somefile.pdf"));
            Log.d("a","download done!");
            //gửi tín hiệu cho Activity là đã load xong
            Intent intent1=new Intent();
            intent1.setAction("DOWNLOAD_DONE");
            intent1.putExtra("message","load xong !");
            sendBroadcast(intent1); //gửi phát sóng
            Log.d("a","sended");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // chỉ được gọi 1 lần khi startService()
    @Override
    public void onCreate() {
        super.onCreate();
    }


//        @Override
//    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
//        Log.d("a","service is started ");
//        //thực hiện download file từ internet
//        try {
//            int result=DownloadFile(new URL("http://www.amazon.com/somefile.pdf"));
//            Log.d("a","download done!");
//            //gửi tín hiệu cho Activity là đã load xong
//            Intent intent1=new Intent();
//            intent1.setAction("DOWNLOAD_DONE");
//            intent1.putExtra("message","load xong !");
//            sendBroadcast(intent1); //gửi phát sóng
//            Log.d("a","sended");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return START_STICKY;
//    }

    private int DownloadFile(URL url) {

        //công việc tốn 20s mới xong

        try {
            //công việc
            for(int i=0;i<20;++i){
                Log.d("a",i+"");
                Thread.sleep(1000);
            }

            //Thread.sleep(20000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } return 100;
    }
    public void load_anh(String url){
        try {
            URL url1=new URL(url);

            InputStream inputStream=url1.openConnection().getInputStream();
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);



            Log.d("a","ok");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
