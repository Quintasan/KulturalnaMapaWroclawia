package me.michalzajac.kulturalnamapawrocawia.activities;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.michalzajac.kulturalnamapawrocawia.R;
import me.michalzajac.kulturalnamapawrocawia.databinding.ActivityEventDetailsBinding;
import me.michalzajac.kulturalnamapawrocawia.models.Event;

public class EventDetailsActvity extends AppCompatActivity {

    @Bind(R.id.backdrop) ImageView backdrop;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.activity_event_details_navigate) FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityEventDetailsBinding activityEventDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_event_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Event selectedEvent = intent.getParcelableExtra("selectedEvent");
        activityEventDetailsBinding.setEvent(selectedEvent);

        collapsingToolbarLayout.setTitle(selectedEvent.toString());
        Glide.with(this).load(selectedEvent.eventImage).centerCrop().into(backdrop);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "TEST", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
