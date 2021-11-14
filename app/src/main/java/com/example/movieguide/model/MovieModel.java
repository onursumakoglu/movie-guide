package com.example.movieguide.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MovieModel implements Serializable {

    @SerializedName("Title")
    private String movieName;

    @SerializedName("Genre")
    private String category;

    @SerializedName("Plot")
    private String plot;

    @SerializedName("Poster")
    private String posterUrl;

    @SerializedName("Year")
    private String year;

    @SerializedName("Released")
    private String released;

    @SerializedName("Runtime")
    private String runtime;

    @SerializedName("Director")
    private String director;

    @SerializedName("Actors")
    private String actors;

    @SerializedName("Language")
    private String language;

    @SerializedName("Country")
    private String country;

    @SerializedName("Awards")
    private String awards;

    @SerializedName("imdbRating")
    private String rating;

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

    public String getYear() {
        return year;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getDirector() {
        return director;
    }

    public String getActors() {
        return actors;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getAwards() {
        return awards;
    }

    public String getRating() {
        return rating;
    }

}
