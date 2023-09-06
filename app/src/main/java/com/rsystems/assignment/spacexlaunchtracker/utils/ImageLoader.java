package com.rsystems.assignment.spacexlaunchtracker.utils;

import android.widget.ImageView;

import com.rsystems.assignment.spacexlaunchtracker.R;
import com.squareup.picasso.Picasso;

public class ImageLoader {
    public static void loadThumbImage(String url, ImageView imageView) {
        Picasso.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }
}
