package com.example.parkinsonaccelerometr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goto_WatchVideo = (Button) findViewById(R.id.main_window_goto_WatchVideo);
        goto_WatchVideo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, WatchVideo.class);
                startActivity(intent);
            }

        });
    }



}
