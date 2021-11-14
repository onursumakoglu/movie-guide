package com.example.movieguide.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieguide.R;
import com.example.movieguide.model.MovieModel;
import com.example.movieguide.view.MovieDetailsActivity;
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
        Picasso.get().load(movieModel.getPosterUrl()).placeholder(R.drawable.loading_animation).into(holder.imagePoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainToDetailsIntent = new Intent(holder.itemView.getContext(), MovieDetailsActivity.class);
                mainToDetailsIntent.putExtra("movieModel", movieModel);
                holder.itemView.getContext().startActivity(mainToDetailsIntent);
            }
        });
    }

    // i set this value to 1 by default, as it only returns a movie
    //   if the api returns a result. If the data from the api was in
    //     the form of a list, then I would get it in the form of a list in
    //       the recylerViewAdapter and set the length of the list as count. i
    //         used recyclerView for easier code editing in case there are other api's
    //           that can return a list.
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
