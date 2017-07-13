package com.example.bhanu.hw2_movies;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by bhanu on 1/24/2017.
 */

public class Moviez implements Serializable{

    public String Name,Description,Genre,IMDB;
    public int Rating,Year;
    public Moviez(String Name, String Description, String Genre, int Rating, int Year, String IMDB)
    {
        this.Name=Name;
        this.Description=Description;
        this.Genre=Genre;
        this.Rating=Rating;
        this.Year=Year;
        this.IMDB=IMDB;
    }

    public String print(){
        return Name+" "+Description+" "+Genre+" "+IMDB+" "+Year;
    }

}
