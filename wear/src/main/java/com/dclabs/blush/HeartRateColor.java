package com.dclabs.blush;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.hardware.Sensor;
import android.view.View;

import java.util.logging.Handler;

/**
 * Created by dhimdisd on 2/17/15.
 */
//Class that updates the
public class HeartRateColor {

    private Sensor heartRateSensor;
    private View view;
    private Handler handler;

    ////
    //  0xffE91A1A stress 110 - 150
    //blush 70 80-90

    //0xff3D4FF0 chill  60/50
    //below 40 deep blue  > 40

    private static int dead = 0xff607D8B;
    private static int chill = 0xff03A9FA;
    private static int green = 0xff00C853;
    private static int blush =  0xffE91E63;
    private  static  int ham =   0xffE91E63;



    public HeartRateColor(Sensor heartRateSensor, View view){
        this.heartRateSensor = heartRateSensor;
        this.view = view;
    }

    //updates view based of color
    public void updateViewWithHeartRate(float heartRate){

        ObjectAnimator colorFade = ObjectAnimator.ofObject(view,
                                        "backgroundColor",
                                        new ArgbEvaluator(),
                                        0xffffffff,
                                        getColorForHeartBeat(heartRate)
                                        );
        colorFade.setDuration(2000);
        colorFade.start();

        //once done vibrate watch

    }

    private int didHeartRangeChange(float heartRate){
       return 0;
    }


    private int getColorForHeartBeat(float heartBeat)
    {
        if (heartBeat < 40){
            return this.dead;
        }else if (40 <= heartBeat && heartBeat < 60 ){
            return this.chill;
        }
        else if (60 <= heartBeat && heartBeat < 80 ){
            return this.green;
        }else if (80 <= heartBeat && heartBeat < 100 ){
            return this.blush;
        }else if (heartBeat >= 100)  {
            return this.ham;
        }

        return this.dead;

    }

}
