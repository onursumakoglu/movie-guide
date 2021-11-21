package com.example.movieguide.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.movieguide.adapter.RecyclerViewAdapter;
import com.example.movieguide.databinding.ActivityMainSearchBinding;
import com.example.movieguide.model.MovieModel;
import com.example.movieguide.service.MovieAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainSearchActivity extends AppCompatActivity {

    ActivityMainSearchBinding activityMainSearchBinding;
    String BASE_URL = "https://www.omdbapi.com/";
    Retrofit retrofit;
    MovieAPI movieAPI;
    Call<MovieModel> movieCall;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    SearchView searchView;
    Button searchButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainSearchBinding = ActivityMainSearchBinding.inflate(getLayoutInflater());
        View view = activityMainSearchBinding.getRoot();
        setContentView(view);

        // i preferred this method because i think
        //  it is a more useful method than the findViewById(R.id.recyclerView).
        recyclerView = activityMainSearchBinding.recyclerView;
        searchView = activityMainSearchBinding.searchView;
        searchButton = activityMainSearchBinding.searchButton;
        progressBar = activityMainSearchBinding.loadingProgress;

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(false);
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieAPI = retrofit.create(MovieAPI.class);
    }

    public void searchMovie(View view){
        String movieName;
        movieName = searchView.getQuery().toString();
        fetchData(movieName);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void fetchData(String movieName){

        movieCall = movieAPI.getData(movieName);

        // i didn't use rxjava library because data
        //   in api is not refreshed frequently
        movieCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call,  @NonNull Response<MovieModel> response) {
                if (response.isSuccessful()){
                    if (response.body().getMovieName() != null){
                        MovieModel movieModel = response.body();
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainSearchActivity.this));
                        recyclerViewAdapter = new RecyclerViewAdapter(movieModel);
                        recyclerView.setAdapter(recyclerViewAdapter);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    else {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),
                                "The movie you were looking for was not found.",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(
                        getApplicationContext(),
                        "Please check your internet connection or connect to another network.",
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}