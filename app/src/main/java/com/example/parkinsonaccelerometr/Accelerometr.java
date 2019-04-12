package com.example.parkinsonaccelerometr;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import android.os.Environment;
import android.widget.TextView;

import com.androidplot.xy.XYPlot;

public class Accelerometr extends AppCompatActivity {

    //DONE make write to file
    //TODO make graph for accelerometr
    //TODO make settings
    //TODO add gyroscope graph
    //TODO calibration
    //TODO what if they dont have SDcard? Do we write on sdcard or on device?
    //https://github.com/halfhp/androidplot/blob/master/docs/quickstart.md
    SensorManager sensorManager ;
    Sensor sensor;
    boolean isPresent=false;
    boolean isStart=false;
    final String ACCELEROMETR_CRD ="Accelerometr_Coordinates";
    private XYPlot plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometr);
        plot=(XYPlot) findViewById(R.id.Accelerometr_graph);

        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(sensors.size()>=0) {
            isPresent = true;
            sensor = sensors.get(0);

        }
        Button stopRecordButton = (Button) findViewById(R.id.Accelerometr_window_stop_record);
        stopRecordButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                isStart=false;
                finish();
                System.exit(0);
            }

        });
        Button startRecordButton = (Button) findViewById(R.id.Accelerometr_window_start_record);
        startRecordButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                isStart=true;
            }

        });
        Button readRecordButton = (Button) findViewById(R.id.Accelerometr_window_read_record);
        readRecordButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                readFile();
            }

        });
    }


    void writeFile(float x, float y, float z){
        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(ACCELEROMETR_CRD, MODE_APPEND)));
            String out=Float.toString(x)+","+Float.toString(y)+","+Float.toString(z)+";";
            bw.write(out);
            bw.newLine();
            bw.close();
            Log.d("ACCELEROMETR_CRD","Тест записан");
        } catch (FileNotFoundException e){
           e.printStackTrace();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void readFile() {
        try
        {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(ACCELEROMETR_CRD)));
            String str = "";
            // читаем содержимое
            String result="";
            while ((str = br.readLine()) != null) {
                result=result+str+"\n";
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (isPresent) {
            sensorManager.registerListener(sel,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isPresent) {
            sensorManager.unregisterListener(sel);
        }

    }

    SensorEventListener sel = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(isStart){
                float x=event.values[0];
                float y=event.values[1];
                float z=event.values[2];
                writeFile(x,y,z);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}

