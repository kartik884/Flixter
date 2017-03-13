package com.knyamagoudar.flixter.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.knyamagoudar.flixter.R;
import com.knyamagoudar.flixter.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.knyamagoudar.flixter.R.id.imageView;
import static com.knyamagoudar.flixter.R.id.ivHitMovie;
import static com.knyamagoudar.flixter.R.id.tvOverview;
import static com.knyamagoudar.flixter.R.id.tvTitle;

/**
 * Created by knyamagoudar on 3/7/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder{
      TextView title;
      TextView overView;
      ImageView posterImage;
    }

    private static class HitViewHolder{
        ImageView hitMovieImage;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context,android.R.layout.simple_list_item_1,movies);
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        int viewType = this.getItemViewType(position);

        if(viewType == 0) {
            ViewHolder viewHolder;
            Movie movie = getItem(position);
            int orientation;
            View v = convertView;
            if(v == null){

                viewHolder = new ViewHolder();

                LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = layoutInflater.inflate(R.layout.item_movie,parent,false);

                viewHolder.title = (TextView) v.findViewById(tvTitle);
                viewHolder.overView = (TextView) v.findViewById(tvOverview);
                viewHolder.posterImage = (ImageView) v.findViewById(imageView);
                viewHolder.posterImage.setImageResource(0);

                v.setTag(viewHolder);

            }else {
                // View is being recycled, retrieve the viewHolder object from tag
                viewHolder = (ViewHolder) v.getTag();
            }

            viewHolder.title.setText(movie.getTitle());
            viewHolder.overView.setText(movie.getOverview());

            orientation = v.getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.posterImage);

            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Picasso.with(getContext()).load(movie.getBackDropPath()).into(viewHolder.posterImage);
            }

            return v;
        }else{
            HitViewHolder hitViewHolder;
            Movie movie = getItem(position);
            int orientation;
            View v = convertView;

            if(v == null){

                hitViewHolder = new HitViewHolder();

                LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = layoutInflater.inflate(R.layout.item_movie_hit, parent, false);

                hitViewHolder.hitMovieImage = (ImageView) v.findViewById(ivHitMovie);
                hitViewHolder.hitMovieImage.setImageResource(0);

                v.setTag(hitViewHolder);

            }else {
                hitViewHolder = (HitViewHolder) v.getTag();
            }
            orientation = v.getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Picasso.with(getContext()).load(movie.getFullImage()).into(hitViewHolder.hitMovieImage);
            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Picasso.with(getContext()).load(movie.getFullImage()).into(hitViewHolder.hitMovieImage);
            }

            return v;
        }

    }

}
