package com.se2.team3.fpms;

import java.util.ArrayList;

/**
 * Created by Scott on 3/30/2015.
 */
public class AircraftMotionManager
{
    private static AircraftMotionManager instance;
    private ArrayList<AircraftMotionListener> listeners = new ArrayList<AircraftMotionListener>();

    public static synchronized AircraftMotionManager getInstance() {
        if (instance == null)
            instance = new AircraftMotionManager();

        return instance;
    }




            new Thread(new Runnable() {
                public void run() {
                    Bitmap b = loadImageFromNetwork("http://example.com/image.png");
                    mImageView.setImageBitmap(b);
                }
            }).start();

    }
}
