package me.michalzajac.kulturalnamapawrocawia.activities;


import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.michalzajac.kulturalnamapawrocawia.R;
import me.michalzajac.kulturalnamapawrocawia.api.API;
import me.michalzajac.kulturalnamapawrocawia.databinding.ActivityRouteDetailsBinding;
import me.michalzajac.kulturalnamapawrocawia.models.OptimizedRouteData;
import me.michalzajac.kulturalnamapawrocawia.models.Route;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RouteDetailsActvity extends AppCompatActivity
        implements ConnectionCallbacks, OnConnectionFailedListener {

    private final static String TAG = RouteDetailsActvity.class.getSimpleName();

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private String navigationString;
    private API.APIInterface _api;
    private Route selectedRoute;

    @Bind(R.id.backdrop) ImageView backdrop;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.activity_route_details_navigate) FloatingActionButton floatingActionButton;
    @Bind(R.id.activity_route_details_distance) TextView distance;
    @Bind(R.id.activity_route_details_duration) TextView duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _api = API.getClient();

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        ActivityRouteDetailsBinding activityRouteDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_route_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        selectedRoute = intent.getParcelableExtra("selectedRoute");
        activityRouteDetailsBinding.setRoute(selectedRoute);

        collapsingToolbarLayout.setTitle(selectedRoute.toString());
        Glide.with(this).load(selectedRoute.getRouteImage()).centerCrop().into(backdrop);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(navigationString == null ? selectedRoute.googleMapsUrl() : navigationString));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            Call<OptimizedRouteData> query = _api.optimize(selectedRoute.getId(), mLastLocation.getLatitude(),
                    mLastLocation.getLongitude());
            query.enqueue(new Callback<OptimizedRouteData>() {
                @Override
                public void onResponse(Response<OptimizedRouteData> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        OptimizedRouteData data = response.body();
                        Log.d(TAG, response.message());
                        navigationString = data.getUrl();
                        Resources res = getResources();
                        distance.setText(String.format(res.getString(R.string.distance), data.getDistanceKilometers()));
                        duration.setText(String.format(res.getString(R.string.duration), data.getDurationHours()));

                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {}

    @Override
    public void onConnectionFailed(ConnectionResult result) {
    }

}