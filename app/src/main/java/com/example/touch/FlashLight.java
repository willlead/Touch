package com.example.touch;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;

// CameraManager 클래스를 사용하여 플레시 기능을 제어
public class FlashLight {

    Context context;
    String cameraID;
    CameraManager cameraManager;

    public FlashLight(Context c) throws CameraAccessException {
        super();
        context = c;
        cameraManager = (CameraManager)context.getSystemService(Context.CAMERA_SERVICE);
        cameraID = getCameraID();
    }

    public String getCameraID() throws CameraAccessException{
        String[] cameraIDs = cameraManager.getCameraIdList();
        for(String id:cameraIDs)
        {
            CameraCharacteristics info = cameraManager.getCameraCharacteristics(id);
            Boolean flashAvailable = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE); // 플레쉬 가능 여부 정보
            Integer lensFacing = info.get(CameraCharacteristics.LENS_FACING);

            if(flashAvailable != null && flashAvailable
                    && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK){
                return id;
            }
        }
        return null;
    }

    public void flashOn() throws CameraAccessException
    {
        cameraManager.setTorchMode(cameraID, true);
    }

    public void flashOff() throws CameraAccessException
    {
        cameraManager.setTorchMode(cameraID, false);
    }
}
