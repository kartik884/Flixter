package com.knyamagoudar.flixter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by knyamagoudar on 3/7/17.
 */

public class Movie implements Serializable{
    String posterPath;
    String title;
    String overview;
    String backDropPath;
    long stars;

    public Movie(JSONObject jsonObject) throws JSONException{
        this.posterPath = jsonObject.getString("poster_path");
        this.title = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backDropPath = jsonObject.getString("backdrop_path");
        this.stars = jsonObject.getLong("vote_average");
    }

    public int getType() {
        if(stars >= 5.0){
            return 1;
        }else{
            return 0;
        }
    }

    public long getStars() {
        return stars;
    }

    public String getBackDropPath() {
        return String.format("https://image.tmdb.org/t/p/w780/%s",backDropPath);
    }

    public String getFullImage() {
        return String.format("https://image.tmdb.org/t/p/w1280/%s",backDropPath);
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public static ArrayList<Movie> fromJsonArray(JSONArray array){
        ArrayList<Movie> results = new ArrayList<>();

        for(int i=0;i<array.length();i++){
            try{
                results.add(new Movie(array.getJSONObject(i)));
            }catch (JSONException ex){
                ex.printStackTrace();
            }
        }
        return results;
    }
}
