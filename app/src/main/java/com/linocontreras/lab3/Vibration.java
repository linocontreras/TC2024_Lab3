package com.linocontreras.lab3;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Vibration extends AppCompatActivity {

    Vibrator vibrator;
    Toast toast;
    long pattern[] = {200, 200, 200, 100, 0, 100, 100, 100, 200, 200, 200, 100, 0, 100, 100, 100};

    private void vibrate(){
        vibrator.vibrate(pattern, -1);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibration);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        toast = Toast.makeText(getApplicationContext(), "Vibrating...", Toast.LENGTH_LONG);
        vibrate();
    }
}
