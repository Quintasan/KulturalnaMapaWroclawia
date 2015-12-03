package me.michalzajac.kulturalnamapawrocawia;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class BindingUtils {
    @BindingAdapter("android:text")
    public static void setDate(TextView textView, Date date) {
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, Locale.getDefault());
        textView.setText(dateFormat.format(date));
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .centerCrop()
                .into(imageView);
    }
}
