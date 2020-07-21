package com.example.teamworks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamworks.model.ImageList;
import com.example.teamworks.utils.RetroFitEngine;
import com.example.teamworks.utils.UserAPICall;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageViewFragment extends Fragment {
    private List<ImageList> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageAdapter mAdapter;

    public ImageViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        callData();

        return view;
    }

    public void callData() {
        UserAPICall userAPICall = RetroFitEngine.getRetrofit().create(UserAPICall.class);
        Call<List<ImageList>> callEnqueue = userAPICall.getData();
        callEnqueue.enqueue(new Callback<List<ImageList>>() {
            @Override
            public void onResponse(Call<List<ImageList>> call, Response<List<ImageList>> response) {
                if (response.code() == 200) {
                    movieList = response.body();
                    loadData();
                } else {
                    Toast.makeText(getContext(), "Server Problem", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ImageList>> call, Throwable t) {
                Toast.makeText(getContext(), "Network Problem", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadData() {
        mAdapter = new ImageAdapter(getContext(),movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}
