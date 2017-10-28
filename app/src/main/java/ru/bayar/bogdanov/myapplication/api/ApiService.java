package ru.bayar.bogdanov.myapplication.api;


import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.bayar.bogdanov.myapplication.api.model.Comment;
import ru.bayar.bogdanov.myapplication.api.model.Post;
import ru.bayar.bogdanov.myapplication.api.model.User;
import rx.Observable;

public interface ApiService {

    @GET("posts/{post_id}")
    Observable<Post> getPost(@Path("post_id") int id);

    @GET("comments/{comment_id}")
    Observable<Comment> getComment(@Path("comment_id") int id);

    @GET("users")
    Observable<ArrayList<User>> getUsers();
}
