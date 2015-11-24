package me.michalzajac.kulturalnamapawrocawia.activities;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.michalzajac.kulturalnamapawrocawia.R;
import me.michalzajac.kulturalnamapawrocawia.models.Route;

public class RouteDetailsActvity extends AppCompatActivity {

    private final static String TAG = RouteDetailsActvity.class.getSimpleName();

    @Bind(R.id.backdrop) ImageView backdrop;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.activity_route_details_navigate) FloatingActionButton floatingActionButton;
    @Bind(R.id.activity_route_details_description) TextView description;
    @Bind(R.id.activity_route_details_information) TextView information;
    @Bind(R.id.activity_route_details_points) TextView points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        final Route selectedRoute = intent.getParcelableExtra("selectedRoute");

        collapsingToolbarLayout.setTitle(selectedRoute.toString());
        Log.d(TAG, selectedRoute.routeImage);
        Glide.with(this).load(selectedRoute.routeImage).centerCrop().into(backdrop);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(selectedRoute.googleMapsUrl()));
                startActivity(intent);
            }
        });
        description.setText(selectedRoute.description);
        information.setText("Tutaj będą wyliczane jakieś bzdury");
        points.setText("Tutaj trzeba wstawić punkty w trasie");
    }
}