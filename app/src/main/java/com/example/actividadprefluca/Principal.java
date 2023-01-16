package com.example.actividadprefluca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {

    Button boton1;
    Button boton2;
    Button boton3;
    private NotificationManager notificador;
    String CHANNEL_ID = "23";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        boton1 = (Button) findViewById(R.id.button);
        boton2 = (Button) findViewById(R.id.button2);
        boton3 = (Button) findViewById(R.id.button3);
        createNotificationChannel();

        Intent intent = new Intent(Principal.this,MainActivity2.class);
        notificador = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builderNot = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setContentTitle("ALERTA")
                .setContentText("Hola que tal")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setTicker("AVISO DE NOTIFICACIÓN");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Principal.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_MUTABLE);
        builderNot.setContentIntent(resultPendingIntent);

        builderNot.setAutoCancel(true);


        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Principal.this, OpcionesPreferencias.class);
                startActivity(i);
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Principal.this);
                Log.i("","Opción 1: " + pref.getBoolean("clave1",false));
                Log.i("","Opción 2: " + pref.getString("clave2","No asignada"));
                Log.i("","Opción 3: " + pref.getString("clave3","No asignada"));
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification n = builderNot.build();
                builderNot.setFullScreenIntent(resultPendingIntent, true);
                notificador.notify(1,n);
            }
        });

    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}