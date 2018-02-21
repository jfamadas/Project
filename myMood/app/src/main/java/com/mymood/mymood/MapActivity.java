package com.mymood.mymood;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, OnMyLocationButtonClickListener, View.OnClickListener {

    private static final int REQUEST_CODE = 102 ;//OnMyLocationClickListener {

    private GoogleMap mMap;
    private Button btn_marker, btn_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        // Assignem cada variable de la activitat a la seva corresponent en el layout
        btn_marker = (Button) findViewById(R.id.btn_marker);
        btn_2 = (Button) findViewById(R.id.btn_2);


        // Funcions que fa cada variable
        btn_2.setOnClickListener(this);
        btn_marker.setOnClickListener(this);


        int status  = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (status== ConnectionResult.SUCCESS) {


            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        else{
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status,(Activity)getApplicationContext(),10);
            dialog.show();
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float initialZoom = 16;
        System.out.println("\n\n\n\nInicialitzacio de mapa\n\n\n\n");


        //Type of Map
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationButtonClickListener(this);
            System.out.println("\n\n\n\nPermisos correctes\n\n\n\n");
            //mMap.setOnMyLocationClickListener(this);

        } else {

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            // Show rationale and request permission.
        }

        // Activem les settings de google maps que volguem (zoom, ubicacion,...)
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        // D'inici fiquem marker a bcn, movem la camara alla i fem zoom a 16
        LatLng barcelona = new LatLng(41.390205, 2.154007);
        mMap.addMarker(new MarkerOptions().position(barcelona).title("Marker in Barcelona"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(barcelona,initialZoom));
    }


    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }


    // MAP BUTTONS
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_marker:
                Location location = mMap.getMyLocation();
                double lat = location.getLatitude();
                double lng = location.getLongitude();
                LatLng location_coord = new LatLng(lat,lng);


                BitmapDescriptor bmd = BitmapDescriptorFactory.fromAsset("mamut.bmp");
                String marker_txt = "myMood testing: prova de que poguem posar un text en el marker";
                mMap.addMarker(new MarkerOptions().position(location_coord).title("myMood").icon(bmd).snippet(marker_txt).flat(true));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location_coord));

                break;
            case R.id.btn_2:
                break;
        }

    }
}
