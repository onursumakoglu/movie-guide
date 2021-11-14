package com.example.movieguide.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieguide.databinding.ActivityMovieDetailsBinding;
import com.example.movieguide.model.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MovieDetailsActivity extends AppCompatActivity {

    ActivityMovieDetailsBinding activity_movie_details;
    MovieModel movieModel;
    ImageView posterImage;
    TextView movieName;
    TextView movieYear;
    TextView runtime;
    TextView rating;
    TextView plot;
    TextView genres;
    TextView director;
    TextView actors;
    TextView country;
    TextView language;
    TextView awards;
    TextView released;

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

        setTitle(movieModel.getMovieName());

        posterImage = activity_movie_details.imagePoster;
        movieName = activity_movie_details.textMovieName;
        movieYear = activity_movie_details.textMovieYear;
        runtime = activity_movie_details.textRuntime;
        rating = activity_movie_details.textRating;
        plot = activity_movie_details.textPlot;
        genres = activity_movie_details.textGenres;
        director = activity_movie_details.textDirector;
        actors = activity_movie_details.textActor;
        country = activity_movie_details.textCountry;
        language = activity_movie_details.textLanguage;
        awards = activity_movie_details.textAwards;
        released = activity_movie_details.textReleased;

        getMovieDetails();
    }

    public void getMovieDetails(){
        Picasso.get().load(movieModel.getPosterUrl()).into(posterImage);
        movieName.setText(movieModel.getMovieName());
        movieYear.setText(movieModel.getYear());
        runtime.setText(movieModel.getRuntime());
        rating.setText(movieModel.getRating());
        plot.setText(movieModel.getPlot());
        genres.setText(movieModel.getCategory());
        director.setText(movieModel.getDirector());
        actors.setText(movieModel.getActors());
        country.setText(movieModel.getCountry());
        language.setText(movieModel.getLanguage());
        awards.setText(movieModel.getAwards());
        released.setText(movieModel.getReleased());
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