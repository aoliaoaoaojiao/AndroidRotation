package com.aoliaoaojiao.AndroidRotaion;

import android.view.IRotationWatcher;

import com.aoliaoaojiao.AndroidRotaion.wrappers.ServiceManager;

public final class Device {

    /**
     * Logical display identifier
     */
    private int displayId;


    public static int lastRotation = -1;

    public String rotationStrTemplate = "{\"width\":%d,\"height\":%d,\"rotation\":%d}";

    public Device() throws ConfigurationException {
        DisplayInfo displayInfo = ServiceManager.getDisplayManager().getDisplayInfo(displayId);
        if (displayInfo == null) {
            Ln.e("Display " + displayId + " not found\n" + LogUtils.buildDisplayListMessage());
            throw new ConfigurationException("Unknown display id: " + displayId);
        }

        lastRotation = displayInfo.getRotation();
        Size size = displayInfo.getSize();
        Ln.i(String.format(rotationStrTemplate,size.getWidth(),size.getHeight(),displayInfo.getRotation()));
        ServiceManager.getWindowManager().registerRotationWatcher(new IRotationWatcher.Stub() {
            @Override
            public void onRotationChanged(int rotation) {
                synchronized (Device.this) {
                    if (lastRotation%2!=rotation%2){
                        lastRotation = rotation;
                        size.rotate();
                    }
                    Ln.i(String.format(rotationStrTemplate,size.getWidth(),size.getHeight(),rotation));
                }
            }
        }, displayId);

    }

}
