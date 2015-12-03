package me.michalzajac.kulturalnamapawrocawia.activities;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.michalzajac.kulturalnamapawrocawia.R;
import me.michalzajac.kulturalnamapawrocawia.databinding.ActivityRouteDetailsBinding;
import me.michalzajac.kulturalnamapawrocawia.models.Route;

public class RouteDetailsActvity extends AppCompatActivity {

    private final static String TAG = RouteDetailsActvity.class.getSimpleName();

    @Bind(R.id.backdrop) ImageView backdrop;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.activity_route_details_navigate) FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRouteDetailsBinding activityRouteDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_route_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        final Route selectedRoute = intent.getParcelableExtra("selectedRoute");
        activityRouteDetailsBinding.setRoute(selectedRoute);

        collapsingToolbarLayout.setTitle(selectedRoute.toString());
        Glide.with(this).load(selectedRoute.routeImage).centerCrop().into(backdrop);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(selectedRoute.googleMapsUrl()));
                startActivity(intent);
            }
        });
    }
}