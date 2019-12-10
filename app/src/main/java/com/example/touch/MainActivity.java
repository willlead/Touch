package com.example.touch;

import androidx.appcompat.app.AppCompatActivity;

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
                try {
                    if(isChecked){
                        flashLight.flashOn();
                    } else{
                        flashLight.flashOff();
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
