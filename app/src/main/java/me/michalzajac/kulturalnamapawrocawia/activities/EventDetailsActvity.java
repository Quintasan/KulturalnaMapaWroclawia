package me.michalzajac.kulturalnamapawrocawia.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.michalzajac.kulturalnamapawrocawia.R;
import me.michalzajac.kulturalnamapawrocawia.models.Event;

public class EventDetailsActvity extends AppCompatActivity {

    @Bind(R.id.backdrop) ImageView backdrop;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.activity_event_details_navigate) FloatingActionButton floatingActionButton;
    @Bind(R.id.activity_event_details_description) TextView description;
    @Bind(R.id.activity_event_details_starts) TextView starts;
    @Bind(R.id.activity_event_details_ends) TextView ends;
    @Bind(R.id.activity_event_details_additional_information) TextView additional_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Event selectedEvent = intent.getParcelableExtra("selectedEvent");

        collapsingToolbarLayout.setTitle(selectedEvent.toString());
        Glide.with(this).load(selectedEvent.eventImage).centerCrop().into(backdrop);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "TEST", Toast.LENGTH_SHORT).show();
            }
        });
        description.setText(selectedEvent.description);
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, Locale.getDefault());
        starts.setText(dateFormat.format(selectedEvent.starts));
        ends.setText(dateFormat.format(selectedEvent.ends));
        additional_information.setText(selectedEvent.price);
    }
}
