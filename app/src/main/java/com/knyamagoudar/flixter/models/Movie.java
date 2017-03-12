package com.knyamagoudar.flixter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by knyamagoudar on 3/7/17.
 */

public class Movie {
    String posterPath;
    String title;
    String overview;
    String backDropPath;

    public Movie(JSONObject jsonObject) throws JSONException{
        this.posterPath = jsonObject.getString("poster_path");
        this.title = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backDropPath = jsonObject.getString("backdrop_path");
    }

    public String getBackDropPath() {
        return String.format("https://image.tmdb.org/t/p/w780/%s",backDropPath);
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
