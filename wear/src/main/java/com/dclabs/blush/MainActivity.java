package com.dclabs.blush;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.Log;
import android.support.v4.view.MotionEventCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.Fitness;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MainActivity extends Activity implements SensorEventListener {

    private TextView mTextView;
    private GestureDetector mDetector;
    private Sensor mHeartRateSensor;
    private SensorManager mSensorManager;
    private CountDownLatch latch;
    private HeartRateColor hrc;
    private boolean isDetecting;
    private float heartRate = 0.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        mSensorManager = ((SensorManager)getSystemService(SENSOR_SERVICE));
        mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);

        if (mHeartRateSensor == null){
            //maybe we will just show screen no heart sesnor detected
            //and return
            Log.d("ERROR", "heart rate sensor is null");
        }


        final SensorEventListener sL = this;
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                RelativeLayout v = (RelativeLayout) stub.findViewById(R.id.moodView);
                hrc = new HeartRateColor(mHeartRateSensor, v);

                v.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        if (event.getAction() == MotionEvent.ACTION_UP){
                            try{
                                //check if mheartratesensor is not null
                                //if
//                           latch = new CountDownLatch(3);
                                 mSensorManager.registerListener(sL, mHeartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);
    //                           latch.await();
                                 Thread.sleep(5000);

                                hrc.updateViewWithHeartRate(heartRate);
//                           heartRate = 0;
//                           mSensorManager.unregisterListener(sL);
                            } catch (InterruptedException e) {
                                Log.e("ERROR", e.getMessage(), e);
                            }
                        }
                       return true;
                    }
                });
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
      //  try {
        //    latch.await();
            if(sensorEvent.values[0] > 0){
                heartRate = sensorEvent.values[0];
            }

    //    } catch (InterruptedException e) {
      //      Log.e("ERROR", e.getMessage(), e);
       // }
    }

    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
     //   Log.d(TAG, "accuracy changed: " + i);
    }

}
