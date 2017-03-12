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

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context,android.R.layout.simple_list_item_1,movies);
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        Movie movie = getItem(position);

        ViewHolder viewHolder;

        if(convertView == null){

            viewHolder = new ViewHolder();

            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.item_movie, parent,false);

            viewHolder.title = (TextView) convertView.findViewById(tvTitle);
            viewHolder.overView = (TextView) convertView.findViewById(tvOverview);
            viewHolder.posterImage = (ImageView) convertView.findViewById(imageView);
            viewHolder.posterImage.setImageResource(0);

            convertView.setTag(viewHolder);

        }else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
//        imageView.setImageResource(0);
//
//        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
//        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
//
//        tvTitle.setText(movie.getTitle());
//        tvOverview.setText(movie.getOverview());

        viewHolder.title.setText(movie.getTitle());
        viewHolder.overView.setText(movie.getOverview());

        int orientation = convertView.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.posterImage);

        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(getContext()).load(movie.getBackDropPath()).into(viewHolder.posterImage);
        }

        return convertView;
    }
}
