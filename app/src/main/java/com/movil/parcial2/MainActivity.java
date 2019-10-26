package com.movil.parcial2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Button hab1,hab2,hab3,hab4,bano,cocina;
    private ImageView im_hab1,im_hab2,im_hab3,im_hab4,im_bano,im_cocina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hab1 = findViewById(R.id.button_Habitacion1);
        hab2 = findViewById(R.id.button_Habitacion2);
        hab3 = findViewById(R.id.button_Habitacion3);
        hab4 = findViewById(R.id.button_Habitacion4);
        bano = findViewById(R.id.button_Bano);
        cocina = findViewById(R.id.button_Cocina);
        im_hab1 = findViewById(R.id.imageView_Habitacion1);
        im_hab2 = findViewById(R.id.imageView_Habitacion2);
        im_hab3 = findViewById(R.id.imageView_Habitacion3);
        im_hab4 = findViewById(R.id.imageView_Habitacion4);
        im_bano = findViewById(R.id.imageView_Bano);
        im_cocina = findViewById(R.id.imageView_Cocina);

        Consumer.loadRoomsStatus();
        Log.i("Array", Arrays.toString(Consumer.room_status.toArray()));

        hab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Consumer.updateLights(2);
            }
        });

        hab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Consumer.updateLights(3);
            }
        });

        hab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Consumer.updateLights(4);
            }
        });

        hab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Consumer.updateLights(5);
            }
        });

        bano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Consumer.updateLights(0);
            }
        });

        cocina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Consumer.updateLights(1);
            }
        });

    }


    public void updateImages(){
        ArrayList<ImageView> ingArr = new ArrayList<>();
        ingArr.add(im_bano);
        ingArr.add(im_cocina);
        ingArr.add(im_hab1);
        ingArr.add(im_hab2);
        ingArr.add(im_hab3);
        ingArr.add(im_hab4);
        for (int i = 0; i < Consumer.room_status.size(); i++){
            if(Consumer.room_status.get(i).getStatus()){
                ingArr.get(i).setVisibility(View.GONE);
            }
        }
    }
}
