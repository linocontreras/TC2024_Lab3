package com.linocontreras.lab3;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Notification extends AppCompatActivity {

    android.app.Notification notification;
    NotificationManager notificationManager;

    private static int id = 0;
    Button notify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification = new NotificationCompat.Builder(this)
                .setContentTitle("New notfication")
                .setContentText("You have been notified")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .build();

        notify = findViewById(R.id.notify_btn);
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.notify(id++, notification);
            }
        });
    }
}
