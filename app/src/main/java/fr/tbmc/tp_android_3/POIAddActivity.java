package fr.tbmc.tp_android_3;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by tbmc on 25/09/2017.
 */

public class POIAddActivity extends Activity implements View.OnClickListener, LocationListener
{

    public static String RETURN_STRING = "PointOfInterest";

    private Button btn;

    private EditText etLabel, etDescription;
    private RatingBar rbScore;
    private TextView tvLocation;
    private Location location;

    private LocationManager mLocationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poi_add_layout);

        btn = (Button)findViewById(R.id.btnAddPoi);
        btn.setOnClickListener(this);

        etLabel = (EditText)findViewById(R.id.editTextLabel);
        etDescription = (EditText)findViewById(R.id.editTextDescription);
        rbScore = (RatingBar)findViewById(R.id.ratingBarScore);
        tvLocation = (TextView)findViewById(R.id.textViewLocation);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5, this);
    }

    @Override
    protected void onPause()
    {
        mLocationManager.removeUpdates(this);
        super.onPause();
    }

    @Override
    public void onClick(View v)
    {

        double latitude = 0, longitude = 0;
        if(location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        PointOfInterest poi = new PointOfInterest(
            "" + etLabel.getText(),
            "" + etDescription.getText(),
            latitude, longitude,
            new Date(),
            rbScore.getRating()
        );

        Intent intent = new Intent();
        intent.putExtra(RETURN_STRING, poi);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onLocationChanged(Location location)
    {
        System.out.println(location.getLatitude() + " : " + location.getLongitude());
        tvLocation.setText("Latitude: " + location.getLatitude() + " ; Longitude: " + location.getLongitude());
        this.location = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }
}
