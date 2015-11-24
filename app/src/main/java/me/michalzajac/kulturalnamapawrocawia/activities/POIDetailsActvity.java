package me.michalzajac.kulturalnamapawrocawia.activities;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.michalzajac.kulturalnamapawrocawia.R;
import me.michalzajac.kulturalnamapawrocawia.models.POI;

public class POIDetailsActvity extends AppCompatActivity {

    @Bind(R.id.backdrop) ImageView backdrop;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.activity_poi_details_navigate) FloatingActionButton floatingActionButton;
    @Bind(R.id.activity_poi_details_description) TextView description;
    @Bind(R.id.activity_poi_details_opening_hours) TextView opening_hours;
    @Bind(R.id.activity_poi_details_additional_information) TextView additional_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi_details);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final POI selectedPOI = intent.getParcelableExtra("selectedPOI");
        collapsingToolbarLayout.setTitle(selectedPOI.toString());
        Glide.with(this).load(selectedPOI.poiImage).centerCrop().into(backdrop);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + selectedPOI.latitude + "," + selectedPOI.longitude + "(" + selectedPOI.name + ")");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
        description.setText(selectedPOI.description);
        opening_hours.setText(selectedPOI.openingHours);
        additional_information.setText(selectedPOI.additionalInformation);
    }
}
