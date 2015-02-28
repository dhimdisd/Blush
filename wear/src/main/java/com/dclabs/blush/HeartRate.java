package com.dclabs.blush;

/**
 * Created by dhimdisd on 2/25/15.
 */
public class HeartRate {

    private float heartRate;
    private int accuracy;

    public HeartRate(){
        heartRate = 0;
        accuracy = 0;
    }

    public void updateHeartRate(float heartRate, int accuracy){
        if (accuracy >= this.accuracy){
            this.heartRate = heartRate;
            this.accuracy = accuracy;
        }
    }

    public float getHeartRate(){
        return this.heartRate;
    }

    public float getAccuracy(){
        return this.accuracy;
    }
}
