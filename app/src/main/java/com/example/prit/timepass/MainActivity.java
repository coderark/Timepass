package com.example.prit.timepass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.prit.timepass.model.Movie;
import com.example.prit.timepass.model.MovieResponse;
import com.example.prit.timepass.rest.ApiClient;
import com.example.prit.timepass.rest.ApiInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG=MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private int gridColumns=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rv_grid);
        recyclerView.setHasFixedSize(true);
        layoutManager=new GridLayoutManager(this, gridColumns);
        recyclerView.setLayoutManager(layoutManager);
        ApiInterface apiService= ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call=apiService.getTopRatedMovies(Utils.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies=response.body().getResults();
                recyclerViewAdapter=new GridAdapter(MainActivity.this, movies);
                recyclerView.setAdapter(recyclerViewAdapter);
                Log.d(TAG, "Number of movies receives: "+movies.size());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });

    }
}
