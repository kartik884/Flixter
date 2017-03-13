package com.knyamagoudar.flixter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.knyamagoudar.flixter.models.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by knyamagoudar on 3/12/17.
 */

public class MovieDetailActivity extends Activity{

    TextView title;
    ImageView poster;
    TextView description;
    RatingBar ratingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        poster = (ImageView) findViewById(R.id.ivDetailsImage);
        Picasso.with(this).load(movie.getFullImage()).into(poster);

        title = (TextView) findViewById(R.id.tvDetailsMovieName);
        title.setText(movie.getTitle());

        description = (TextView) findViewById(R.id.tvDetailsDescription);
        description.setText(movie.getOverview());

        ratingBar = (RatingBar) findViewById(R.id.rbDetailsRatingBar);
        ratingBar.setRating(movie.getStars());
    }
}
