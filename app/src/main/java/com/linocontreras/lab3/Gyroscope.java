package com.linocontreras.lab3;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Gyroscope extends AppCompatActivity implements SensorEventListener{

    SensorManager sensorManager;
    Sensor gyroscope;
    TextView roll_txt;
    TextView pitch_txt;
    TextView yaw_txt;
    float roll, pitch, yaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        roll_txt = findViewById(R.id.roll);
        pitch_txt = findViewById(R.id.pitch);
        yaw_txt = findViewById(R.id.yaw);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        roll = event.values[2];
        pitch = event.values[1];
        yaw = event.values[0];

        roll_txt.setText("Roll: " + roll);
        pitch_txt.setText("Pitch: " + pitch);
        yaw_txt.setText("Yaw: " + yaw);

        if(roll * roll >= 1 || pitch * pitch >=1 || yaw * yaw >= 1) {
            sensorManager.unregisterListener(this);
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setTitle("That was fast!");
            dialogBuilder.setMessage("Don't do it again");
            final Gyroscope context = this;
            dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Thanks", Toast.LENGTH_SHORT).show();
                    sensorManager.registerListener(context, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
                }
            });
            AlertDialog dialog = dialogBuilder.create();
            dialog.show();
            //sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
