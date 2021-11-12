package com.example.movieguide.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.movieguide.R;
import com.example.movieguide.adapter.RecyclerViewAdapter;
import com.example.movieguide.databinding.ActivityMainBinding;
import com.example.movieguide.model.MovieModel;
import com.example.movieguide.service.MovieAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // https://www.omdbapi.com/?t=avatar/&apikey=1ed29a31
    // https://www.omdbapi.com/?s=batman&apikey=1ed29a31

    private ActivityMainBinding activityMainBinding;
    private String BASE_URL = "https://www.omdbapi.com/";
    Retrofit retrofit;
    MovieAPI movieAPI;
    Call<MovieModel> movieCall;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    SearchView searchView;
    Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        // it is more useful instead of the findViewById(R.id.recyclerView)
        recyclerView = activityMainBinding.recyclerView;
        searchView = activityMainBinding.searchView;
        searchButton = activityMainBinding.searchButton;

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
    }

    private void fetchData(String movieName){

        movieCall = movieAPI.getData(movieName);

        movieCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call,  @NonNull Response<MovieModel> response) {
                if (response.isSuccessful()){
                    MovieModel movieModel = response.body();
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(movieModel);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                //t.printStackTrace();
                System.out.println("başarısız");
            }
        });

    }

}