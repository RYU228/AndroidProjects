package com.example.ryu.gpstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.location.LocationManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.button1);

        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


                String locationProvider = LocationManager.GPS_PROVIDER;
                Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
                if (lastKnownLocation != null) {
                    double lng = lastKnownLocation.getLatitude();
                    double lat = lastKnownLocation.getLatitude();
                    Log.d("Main", "longtitude=" + lng + ", latitude=" + lat);
                }
            }
        });
    }
}
