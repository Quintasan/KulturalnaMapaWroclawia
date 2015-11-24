package me.michalzajac.kulturalnamapawrocawia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import me.michalzajac.kulturalnamapawrocawia.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment supportMapFragment
                = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Float latitude = intent.getFloatExtra("latitude", 41.37f);
        Float longitude = intent.getFloatExtra("longitude", -134.23f);
        LatLng coordinates = new LatLng(latitude, longitude);
        map.addMarker(new MarkerOptions().position(coordinates).title(name));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 14));
    }
}
