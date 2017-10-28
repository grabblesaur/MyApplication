package ru.bayar.bogdanov.myapplication.ui.cards;


import java.util.ArrayList;

import ru.bayar.bogdanov.myapplication.api.model.Comment;
import ru.bayar.bogdanov.myapplication.api.model.Post;
import ru.bayar.bogdanov.myapplication.api.model.TodoObject;
import ru.bayar.bogdanov.myapplication.api.model.User;
import ru.bayar.bogdanov.myapplication.base.BaseView;

public interface CardsView extends BaseView {
    void onSpecificPostGetSuccess(Post post);
    void onSpecificPostGetError(Throwable throwable);

    void onSpecificCommentGetSuccess(Comment comment);
    void onSpecificCommentGetError(Throwable throwable);

    void onUserGetSuccess(User user);
    void onGetUrlSuccess(String url, String thumbnailUrl);

    void onGetTodosListSuccess(int usersId, ArrayList<TodoObject> todoObjects);
}
