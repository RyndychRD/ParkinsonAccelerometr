package com.example.parkinsonaccelerometr;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class WatchVideo extends AppCompatActivity {

    //Можно воспроизводить видео с веб сервиса, обсудить со смагиным

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_video);

        Button goto_Accelerometr = (Button) findViewById(R.id.WatchVideo_window_goto_Accelerometr);
        goto_Accelerometr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(WatchVideo.this, Accelerometr.class);
                startActivity(intent);
            }

        });

        VideoView videoView = (VideoView) findViewById(R.id.WatchVideo_window_videoPlayer);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() +"/"+R.raw.test));

        videoView.setMediaController(new MediaController(this));
        videoView.setMediaController(null);
        videoView.requestFocus(0);
        videoView.start(); // начинаем воспроизведение автоматически
    }
}
