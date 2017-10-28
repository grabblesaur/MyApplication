package ru.bayar.bogdanov.myapplication.api;


import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.bayar.bogdanov.myapplication.api.model.Comment;
import ru.bayar.bogdanov.myapplication.api.model.Photo;
import ru.bayar.bogdanov.myapplication.api.model.Post;
import ru.bayar.bogdanov.myapplication.api.model.TodoObject;
import ru.bayar.bogdanov.myapplication.api.model.User;
import rx.Observable;

public interface ApiService {

    @GET("posts/{post_id}")
    Observable<Post> getPost(@Path("post_id") int id);

    @GET("comments/{comment_id}")
    Observable<Comment> getComment(@Path("comment_id") int id);

    @GET("users/{user_id}")
    Observable<User> getUser(@Path("user_id") int id);

    @GET("photos/{photo_id}")
    Observable<Photo> getPhoto(@Path("photo_id") int id);

    @GET("todos")
    Observable<ArrayList<TodoObject>> getTodoList(@Query("userId") int usersId);
}
