package sg.edu.rp.c346.id20031634.movieratings;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Movie implements Serializable {
    private int id;
    private String Movie;
    private String Description;
    private int yearReleased;
    private int stars;


    public Movie(int id, String Movie, String Description, int yearReleased, int stars) {
        this.id = id;
        this.Movie = Movie;
        this.Description = Description;
        this.yearReleased = yearReleased;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public Movie setId(int id) {
        this.id = id;
        return this;
    }

    public String getMovie() {
        return Movie;
    }

    public Movie setMovie(String Movie) {
        this.Movie = Movie;
        return this;
    }

    public String getDescription() {
        return Description;
    }

    public Movie setDescription(String Description ) {
        this.Description = Description;
        return this;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public Movie setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public Movie setStars(int stars) {
        this.stars = stars;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        String starsString = "";
        for(int i = 0; i < stars; i++){
            starsString += "* ";
        }
        return starsString;

    }
}

