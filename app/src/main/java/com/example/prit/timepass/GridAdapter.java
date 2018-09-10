package com.example.prit.timepass;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.prit.timepass.model.Movie;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private final String TAG=GridAdapter.class.getSimpleName();
    private List<Movie> mData;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public ImageView thumbnail;
        public ViewHolder(View v){
            super(v);
            title=v.findViewById(R.id.tv_title);
            thumbnail=v.findViewById(R.id.iv_thumbnail);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "Clicked");
            Movie movie=mData.get(getAdapterPosition());
            Intent intent=new Intent(mContext, MovieDetail.class);
            intent.putExtra("id", movie.getId());
            mContext.startActivity(intent);
        }
    }

    public GridAdapter(Context context, List<Movie> data){
        mData=data;
        mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_item_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie=mData.get(position);
        String poster_path=Utils.BASE_IMAGE_URL+movie.getPosterPath();
        Log.d(TAG, poster_path);
        holder.title.setText(movie.getTitle());
        Glide.with(mContext).load(poster_path).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
