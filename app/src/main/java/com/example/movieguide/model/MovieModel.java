package com.example.movieguide.model;

import com.google.gson.annotations.SerializedName;

public class MovieModel {

    @SerializedName("Title")
    private String movieName;

    @SerializedName("Genre")
    private String category;

    @SerializedName("Plot")
    private String plot;

    @SerializedName("Poster")
    private String posterUrl;

    public String getMovieName() {
        return movieName;
    }

    public String getCategory() {
        return category;
    }

    public String getPlot() {
        return plot;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

}
