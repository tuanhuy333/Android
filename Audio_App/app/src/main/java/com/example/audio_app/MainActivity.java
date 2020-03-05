package com.example.audio_app;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //onclick for play button
    public void play(View v) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.mysong);

            //khi phat xong thi stop()
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });

        }

        //play

        mediaPlayer.seekTo(12000);//chay bai nhac toi 12s
        mediaPlayer.start();

        Toast.makeText(this, mediaPlayer.getCurrentPosition()+"", Toast.LENGTH_SHORT).show();

    }

    public void pause(View v) {
        if(mediaPlayer != null){
            mediaPlayer.pause();
        }

    }

    public void stop(View v) {
        stopPlayer();
    }

    public void stopPlayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();

            //set lai tu dau de khi nhan nut play se choi lai bai nhac
            mediaPlayer = null;
        }
    }
}
