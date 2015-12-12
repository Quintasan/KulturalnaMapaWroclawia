package me.michalzajac.kulturalnamapawrocawia.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.michalzajac.kulturalnamapawrocawia.R;

public class WroclawActivity extends AppCompatActivity {

    @Bind(R.id.backdrop) ImageView backdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wroclaw);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        Glide.with(this).load(R.drawable.wroclaw).centerCrop().into(backdrop);
    }

}
