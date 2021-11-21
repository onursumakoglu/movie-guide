package com.example.movieguide.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.movieguide.databinding.ActivityMovieDetailsBinding;
import com.example.movieguide.model.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MovieDetailsActivity extends AppCompatActivity {

    ActivityMovieDetailsBinding activity_movie_details;
    MovieModel movieModel;
    ImageView posterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity_movie_details = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = activity_movie_details.getRoot();
        setContentView(view);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // data could be imported into this activity with several
        //   methods other than serializable, such as the singleton pattern.
        //     i chose this method because i thought this method would be more
        //       suitable for this project.
        Intent intent = getIntent();
        movieModel = (MovieModel) intent.getSerializableExtra("movieModel");

        activity_movie_details.setMovieModel(movieModel);
        posterImage = activity_movie_details.imagePoster;
        Picasso.get().load(movieModel.getPosterUrl()).into(posterImage);

        setTitle(movieModel.getMovieName());
    }

    // i override this method because when i press the
    //   back button in the actionbar i wanted to back to main
    //     activity without clearing main activity data.
    //       like hard back key button.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }
}