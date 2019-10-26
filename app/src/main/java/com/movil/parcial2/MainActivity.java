package com.movil.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button hab1,hab2,hab3,bano,cocina;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hab1 = findViewById(R.id.button_Habitacion1);
        hab2 = findViewById(R.id.button_Habitacion2);
        hab3 = findViewById(R.id.button_Habitacion3);
        bano = findViewById(R.id.button_Bano);
        cocina = findViewById(R.id.button_Cocina);

    }
}
