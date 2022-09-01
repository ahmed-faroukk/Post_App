package com.example.post_app;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("posts")
    public Call<List<post>> getPost(@Query("userId") String userId);
    @POST("posts")
    //body is object of class post
    public Call<post> storepost(@Body post post);

    @POST("posts")
    //map
    public Call<post> storepost_map(@Body HashMap<Object , Object> map);
}
