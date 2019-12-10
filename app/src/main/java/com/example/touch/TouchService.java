package com.example.touch;

import android.app.Service;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.os.IBinder;

public class TouchService extends Service {
    FlashLight flashLight;
    Boolean isRunning = false;

    public TouchService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            flashLight = new FlashLight(this);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //외부에서 StartService() 호출하면 onStartCommand() callback 메서드로 받는다.
        // callback 은 호출하면 자동으로 호출되는 메서드이다.

        //앱에서 실행할 경우
        if (intent.getAction().equals("on")) {
            try {
                flashLight.flashOn();
                isRunning = true;
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        } else if(intent.getAction().equals("off")) {
            try {
                flashLight.flashOff();
                isRunning = false;
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }else {
            //서비스에서 실행할 경우
            isRunning = !isRunning;
            if(isRunning){
                try {
                    flashLight.flashOn();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    flashLight.flashOff();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
