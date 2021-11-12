package com.example.movieguide.service;

import com.example.movieguide.model.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {

    @GET("?&apikey=1ed29a31")
    Call<MovieModel> getData(@Query("t") String movieTitle);

}
