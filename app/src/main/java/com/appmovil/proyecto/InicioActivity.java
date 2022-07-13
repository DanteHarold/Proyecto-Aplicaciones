package com.appmovil.proyecto;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(InicioActivity.this, com.appmovil.proyecto.MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();

        timer.schedule(tarea,7000);
    }
}