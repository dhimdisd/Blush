package com.dclabs.blush;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

public class MainActivity extends Activity /*implements SensorEventListener*/ {

//    private TextView mTextView;
//    private GestureDetector mDetector;
//    private Sensor mHeartRateSensor;
//    private SensorManager mSensorManager;
//    private CountDownLatch latch;
//    private HeartRateColor hrc;
//    private boolean isDetecting;
//    private float heartRate = 0.0f;
//    public ProgressBar bar;
//    final SensorEventListener sL = this;
//    private ProgressTask task = null;
//    private CountDownTimer timer = null;
//    private int accuracy = 0;
//    private HeartRate hr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
//        mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
//        mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
//
//
//        if (mHeartRateSensor == null) {
//            //maybe we will just show screen no heart sensor detected
//            //and return
//            Log.d("ERROR", "heart rate sensor is null");
//        }


//        final SensorEventListener sL = this;

        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(final WatchViewStub stub) {
                final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
                pager.setAdapter(new HeartRateGridPageAdapter(MainActivity.this, getFragmentManager()));

//                RelativeLayout moodFragment = (RelativeLayout) stub.findViewById(R.id.moodFragment);
//                hrc = new HeartRateColor(mHeartRateSensor, moodFragment);
//                bar = (ProgressBar) stub.findViewById(R.id.progressBar);

//                moodFragment.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        if (event.getAction() == MotionEvent.ACTION_UP) {
//                            if (task == null) {
//                                task = new ProgressTask();
//                                task.execute();
//
//                            }
//                            bar.setVisibility(View.VISIBLE);
//                        }
//                        return true;
//                    }
//                });
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//    }
//
//    @Override
//    public void onSensorChanged(SensorEvent sensorEvent) {
//
//        if (sensorEvent.values[0] > 0) {
//            if(hr == null){
//                hr = new HeartRate();
//            }
//            hr.updateHeartRate(
//                    sensorEvent.values[0], accuracy);
//        }
//
//    }
//
//    protected void onStop() {
//        super.onStop();
//        mSensorManager.unregisterListener(this);
//        if(task != null)
//            task.cancel(true);
//        if (timer != null)
//            timer.cancel();
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int i) {
//        accuracy = i;
//    }
//
//    private class ProgressTask extends AsyncTask<Void, Void, Void> {
//
//        private ProgressTask Pt = this;
//        public boolean isTouched = false;
//
//        @Override
//        protected void onPreExecute() {
//
////            mTextView.setVisibility(View.INVISIBLE);
//            mSensorManager.registerListener(
//                    sL, mHeartRateSensor,
//                    SensorManager.SENSOR_DELAY_NORMAL);
//            if (timer != null){
//                timer.cancel();
//                timer = null;
//            }
//        }
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//            long startTime = System.currentTimeMillis(); //fetch starting time
//            while ((hr == null || (hr != null && hr.getAccuracy() < 2)) &&
//                    (System.currentTimeMillis() - startTime) < 60000) {
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            bar.setVisibility(View.GONE);
//            mSensorManager.unregisterListener(sL);
//            task = null;
//            if (hr != null) {
//                hrc.updateViewWithHeartRate(hr.getHeartRate());
////                mTextView.setText(String.valueOf(hr.getHeartRate()) + " " + String.valueOf(hr.getAccuracy()));
////                mTextView.setVisibility(View.VISIBLE);
//                hr = null;
//            } else {
////                mTextView.setVisibility(View.VISIBLE);
//            }
//
//
//
//            timer = new CountDownTimer(30000, 1000) {
//
//                public void onTick(long millisUntilFinished) {
//
//                }
//
//                public void onFinish() {
//                    if (task == null) {
//                        task = new ProgressTask();
//                        task.execute();
//                    }
//                }
//            }.start();
//        }
//    }

}
