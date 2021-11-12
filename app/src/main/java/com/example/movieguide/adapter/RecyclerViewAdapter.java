package com.example.movieguide.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieguide.R;
import com.example.movieguide.model.MovieModel;
import com.squareup.picasso.Picasso;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private MovieModel movieModel;

    public RecyclerViewAdapter(MovieModel movieModel) {
        this.movieModel = movieModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textName.setText(movieModel.getMovieName());
        holder.textCategory.setText(movieModel.getCategory());
        holder.textPlot.setText(movieModel.getPlot());
        Picasso.get().load(movieModel.getPosterUrl()).into(holder.imagePoster);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textCategory;
        TextView textPlot;
        ImageView imagePoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.text_name);
            textCategory = itemView.findViewById(R.id.text_category);
            textPlot = itemView.findViewById(R.id.text_plot);
            imagePoster = itemView.findViewById(R.id.image_poster);

        }
    }
}
