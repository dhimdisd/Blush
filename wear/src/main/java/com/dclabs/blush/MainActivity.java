package com.dclabs.blush;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.Log;

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
    private ProgressBar bar;
    final SensorEventListener sL = this;
    private ProgressTask task = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);


        if (mHeartRateSensor == null) {
            //maybe we will just show screen no heart sensor detected
            //and return
            Log.d("ERROR", "heart rate sensor is null");
        }


        final SensorEventListener sL = this;
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(final WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.textView);
                RelativeLayout v = (RelativeLayout) stub.findViewById(R.id.moodView);
                hrc = new HeartRateColor(mHeartRateSensor, v);
                bar = (ProgressBar) stub.findViewById(R.id.progressBar);

                v.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            if (task == null) {
                                task = new ProgressTask();
                                task.execute();
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

        if (sensorEvent.values[0] > 0) {
            heartRate = sensorEvent.values[0];
        }

    }

    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.d("TAG", "accuracy changed: " + i);
    }

    private class ProgressTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {

            mTextView.setVisibility(View.INVISIBLE);
            bar.setVisibility(View.VISIBLE);
            mSensorManager.registerListener(
                    sL, mHeartRateSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            long startTime = System.currentTimeMillis(); //fetch starting time
            while (heartRate == 0 && (System.currentTimeMillis() - startTime) < 15000) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            bar.setVisibility(View.GONE);
            if (heartRate != 0) {
                hrc.updateViewWithHeartRate(heartRate);
                heartRate = 0;
            } else {
                mTextView.setText("Try Again");
                mTextView.setVisibility(View.VISIBLE);
            }
            mSensorManager.unregisterListener(sL);
            task = null;
        }
    }

}
