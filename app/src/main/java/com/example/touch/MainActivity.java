package com.example.touch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Switch swFlash;
    private FlashLight flashLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swFlash = (Switch)findViewById(R.id.swFlash);
        try {
            flashLight = new FlashLight(this);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        swFlash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent = new Intent(MainActivity.this, TouchService.class);

                //try {
                    if(isChecked){
                        //flashLight.flashOn();
                        intent.setAction("on");
                    } else{
                        //flashLight.flashOff();
                        intent.setAction("off");
                    }
//                } catch (CameraAccessException e) {
//                    e.printStackTrace();
//                }
                startService(intent);

            }
        });
    }
}
