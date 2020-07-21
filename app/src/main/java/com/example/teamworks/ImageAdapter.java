package com.example.teamworks;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.teamworks.model.ImageList;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private List<ImageList> moviesList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, title;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.item_id);
            image = (ImageView) view.findViewById(R.id.item_image);
            title = (TextView) view.findViewById(R.id.item_title);
        }
    }


    public ImageAdapter(Context context,List<ImageList> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ImageList movie = moviesList.get(position);
        holder.title.setText("TITLE : " + movie.getTitle());
        holder.id.setText("ID : " + movie.getId().toString());
       /* if (movie.getUrl() != null) {
            Glide.with(context)
                    .load(movie.getUrl())
                    .placeholder(R.drawable.user)
                    .into(holder.image);
        } else {
            Glide.with(context)
                    .load(movie.getUrl())
                    .placeholder(R.drawable.user)
                    .into(holder.image);
        }*/

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}