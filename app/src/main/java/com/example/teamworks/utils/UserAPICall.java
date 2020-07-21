package com.example.teamworks.utils;


import com.example.teamworks.model.ImageList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserAPICall {

    @GET("/photos")
    Call<List<ImageList>> getData();

}
