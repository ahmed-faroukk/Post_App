package com.example.post_app;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView Title;
    TextView Body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Title = findViewById(R.id.Title);
        Body = findViewById(R.id.Post);
        post post = new post(1, "Codind World", "Course Ads for students who are interested in programming");
        // sendPost(post);
        // GetPost("2" , 2);
         Mapp();
    }


    //............................................................
    void sendPost(post post) {
        //builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // to make retrofit create interface body for interface we had made
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        //call
        Call<post> call = apiInterface.storepost(post);
        call.enqueue(new Callback<com.example.post_app.post>() {
            @Override
            public void onResponse(Call<com.example.post_app.post> call, Response<com.example.post_app.post> response) {
                Title.setText(response.body().getTitle());
                Body.setText(response.body().getBody());

            }

            @Override
            public void onFailure(Call<com.example.post_app.post> call, Throwable t) {
                Title.setText(t.getMessage());
                Body.setText("Error404");

            }
        });
    }
    //............................................................

    public void GetPost(String Userid, int postNum) {
        //builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //converter
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        //call
        Call<List<post>> GetPosts = apiInterface.getPost(Userid);
        GetPosts.enqueue(new Callback<List<post>>() {
            @Override
            public void onResponse(Call<List<post>> call, Response<List<post>> response) {
                Title.setText(response.body().get(postNum).getTitle());
                Title.setText(response.body().get(postNum).getBody());

            }

            @Override
            public void onFailure(Call<List<post>> call, Throwable t) {
                Title.setText(t.getMessage());
                Body.setText("Error404");
            }
        });
    }

    public void Mapp() {
        //map
        HashMap<Object, Object> map = new HashMap<>();
        map.put("title", "this is how is map work");
        map.put("body", "i love map way");
        //builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //converter
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        //call
        Call<post> SenPostBymap = apiInterface.storepost_map(map);
        SenPostBymap.enqueue(new Callback<post>() {
            @Override
            public void onResponse(Call<post> call, Response<post> response) {
                Title.setText(response.body().getTitle());
                Body.setText(response.body().getBody());

            }

            @Override
            public void onFailure(Call<post> call, Throwable t) {
                Title.setText(t.getMessage());
                Body.setText("Error404");            }
        });

    }
}