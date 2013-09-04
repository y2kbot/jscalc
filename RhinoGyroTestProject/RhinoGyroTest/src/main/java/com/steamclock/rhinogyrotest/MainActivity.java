package com.steamclock.rhinogyrotest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
    public static final String TAG = "MainActivity";
    private SensorManager mSensorManager;
    private Sensor mSensor;
    //UI
    private TextView mAccuracy;
    private TextView mTimestamp;
    private TextView mX;
    private TextView mY;
    private TextView mZ;
    private TextView mC;
    private TextView mA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        mAccuracy = (TextView) findViewById(R.id.accuracy);
        mTimestamp = (TextView) findViewById(R.id.timestamp);
        mX = (TextView) findViewById(R.id.valX);
        mY = (TextView) findViewById(R.id.valY);
        mZ = (TextView) findViewById(R.id.valZ);
        mC = (TextView) findViewById(R.id.valC);
        mA = (TextView) findViewById(R.id.valA);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        assert(sensorEvent.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR);

        Log.d(TAG, "event");
        mAccuracy.setText(String.valueOf(sensorEvent.accuracy));
        mTimestamp.setText(String.valueOf(sensorEvent.timestamp));
        mX.setText(String.valueOf(sensorEvent.values[0]));
        mY.setText(String.valueOf(sensorEvent.values[1]));
        mZ.setText(String.valueOf(sensorEvent.values[2]));
        if (sensorEvent.values.length > 3) {
            mC.setText(String.valueOf(sensorEvent.values[3]));
        }
        if (sensorEvent.values.length > 4) {
            mA.setText(String.valueOf(sensorEvent.values[4]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.d(TAG, "accuracy change");
    }
}
