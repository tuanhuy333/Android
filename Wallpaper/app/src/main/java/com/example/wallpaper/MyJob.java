package com.example.wallpaper;

import android.app.WallpaperManager;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyJob extends JobService {

    public boolean onStartJob(JobParameters params) {

        //mảng hình có sẵn
        int[] img = {R.drawable.wall1,R.drawable.wall2,R.drawable.wall3,R.drawable.wall4,
                R.drawable.wall5,R.drawable.wall6,R.drawable.wall7,R.drawable.wall8,
                R.drawable.wall9,R.drawable.wall10,R.drawable.wall11,R.drawable.wall12,
                R.drawable.wall13,R.drawable.wall14,R.drawable.wall15,R.drawable.wall16,
                R.drawable.wall17,R.drawable.wall18,R.drawable.wall19,R.drawable.wall20,
                R.drawable.wall21,R.drawable.wall22,R.drawable.wall23,R.drawable.wall24,
                R.drawable.wall25};

        Log.d("a","set wallpaper");

        //lấy sharePreference
        SharedPreferences sharedPreferences = getSharedPreferences("Img", MODE_PRIVATE);
        int img_hientai = sharedPreferences.getInt("img_hientai", -1);

        //nếu chưa có giá trị img hiện tại thì đặt là hình nền đầu tiên
        if(img_hientai == -1){
            datHinhnen(img[0]);
        }
        Log.d("a",img_hientai+" ");
        Log.d("a",img[0]+" ");

        //set wallpaper
        for (int i = 0; i < img.length; ++i) {
            //nếu hình nền hiện tại == vs trong mảng thì đặt hình nền tiếp theo
            if (img[i] == img_hientai) {
                i+=1;
                Log.d("a",i+"");

                datHinhnen(img[i]);
                break;
            }
            //nếu hình nền hiện tại ở cuối mảng
            if(img_hientai == img[img.length-1]){
                datHinhnen(img[0]);
                break;
            }
        }

        jobFinished(params, false); //false : để đừng lập lịch lại

        return true; //chia làm nhiều thread chứ không chạy trên mainThread
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    private void datHinhnen(int hinhnen) {

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), hinhnen);
        Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 300, 600, true);


        try {
            //setBitmap
            wallpaperManager.setBitmap(bitmap1);
            // wallpaperManager.suggestDesiredDimensions(250, 400);



            //lưu img hiện tại vào SharePreference
            SharedPreferences sharedPreferences = getSharedPreferences("Img", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("img_hientai", hinhnen);
            editor.commit();


            //thông báo
            Toast.makeText(this, "Đã set hình nền", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            //  txt.setText(ex.toString());
        }


    }
}
