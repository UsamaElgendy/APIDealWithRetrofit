package com.example.apidealwithretrofit;


import com.example.apidealwithretrofit.Model.Comment;
import com.example.apidealwithretrofit.Model.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderAPI {
    // here we make Call from retrofit and make a list of posts

    // just use GET to get posts --> that means we want to get data from the server
    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId,
            @Query("_sort") String sort,
            @Query("_order") String order

            // you can put multiple userId to search for it @Query("userId") int userId2
            // if you want to search for a lot of id you can just use Integer[] --> Integer[] userId
            // and in a mainActivity you pass a new Integer[1,2,8,...]

    );

    // you can just create a hashMap to send a key and value --> key = userId and value = name of it
    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameter);


    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);




    @GET
    Call<List<Comment>> getComments(@Url String url);

}
