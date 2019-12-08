package com.example.apidealwithretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.apidealwithretrofit.Model.Comment;
import com.example.apidealwithretrofit.Model.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// JSON URL USER = "https://jsonplaceholder.typicode.com/posts"
// vidio = "https://www.youtube.com/watch?v=TyJEDhauUeQ&list=PLrnPJCHvNZuCbuD3xpfKzQWOj3AXybSaM&index=2"
public class MainActivity extends AppCompatActivity {

    TextView textViewResult;
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        // in baseUrl we put the link of json file without /posts -->
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);


//        getPosts();
        getComments();

    }


    private void getComments() {
        Call<List<Comment>> call = jsonPlaceHolderAPI.getComments("posts/3/comments");
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code : " + response.code());
                    return;
                } else {
                    List<Comment> comments = response.body();
                    for (Comment comment : comments) {
                        String content = "";
                        content += "ID : " + comment.getPostID() + "\n";
                        content += "post ID : " + comment.getId() + "\n";
                        content += "Name : " + comment.getName() + "\n";
                        content += "Email : " + comment.getEmail() + "\n";
                        content += "Text : " + comment.getText() + "\n" + "\n" + "\n";

                        textViewResult.append(content);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }


    private void getPosts() {
        // you can just send a hashMap have a key and value
        Map<String, String> parameter = new HashMap<>();
        parameter.put("userId", "1");
        parameter.put("_sort", "id");
        parameter.put("_order", "desc");
        // first case you just send in getPosts a user id  --> 4,"id","desc"
        // second case you just sent a array of Integer --> new Integer[]{2,3,6},null,null
        // third this ....
        Call<List<Post>> call = jsonPlaceHolderAPI.getPosts(parameter); // you can make it null ...

        // now execute our network request
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code : " + response.code());
                    return;
                } else {
                    // in body() --> it's have a list of posts
                    List<Post> posts = response.body();
                    for (Post post : posts) {
                        String content = "";
                        content += "ID : " + post.getId() + "\n";
                        content += "User ID : " + post.getUserId() + "\n";
                        content += "title : " + post.getTitle() + "\n";
                        content += "Text : " + post.getText() + "\n" + "\n" + "\n";

                        textViewResult.append(content);
                    }
                }
            }

            // onFailure means that sonthing wrong in communication with the server
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }
}
