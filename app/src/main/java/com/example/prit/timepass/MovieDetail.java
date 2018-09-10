package com.example.prit.timepass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.prit.timepass.model.Movie;
import com.example.prit.timepass.rest.ApiClient;

public class MovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
//        Movie movie= ApiClient.getClient()
    }
}
