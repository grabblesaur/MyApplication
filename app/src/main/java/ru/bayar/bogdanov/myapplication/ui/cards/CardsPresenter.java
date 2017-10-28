package ru.bayar.bogdanov.myapplication.ui.cards;


import ru.bayar.bogdanov.myapplication.api.ApiClient;
import ru.bayar.bogdanov.myapplication.api.ApiService;
import ru.bayar.bogdanov.myapplication.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CardsPresenter extends BasePresenter<CardsView> {

    private ApiService mApiService;

    public CardsPresenter() {
        mApiService = ApiClient.getApiService(ApiClient.getClient());
    }

    public void getPost(int id) {
        mApiService.getPost(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(post -> {
                    getView().onSpecificPostGetSuccess(post);
                }, throwable -> {
                    getView().onSpecificPostGetError(throwable);
                });
    }

    public void getComment(int id) {
        mApiService.getComment(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comment -> {
                    getView().onSpecificCommentGetSuccess(comment);
                }, throwable -> {
                    getView().onSpecificCommentGetError(throwable);
                });
    }

    public void getUserList() {
        for(int i = 1; i <= 5; i++) {
            mApiService.getUser(i)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(user -> {
                        getView().onUserGetSuccess(user);
                    });
        }
    }

    public void getPhoto(int i) {
        mApiService.getPhoto(i)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(photo -> {
                    getView().onGetUrlSuccess(photo.getUrl(), photo.getThumbnail());
                });
    }

    public void getTodoList(int usersId) {
        mApiService.getTodoList(usersId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(todoObjects -> {
                    getView().onGetTodosListSuccess(usersId, todoObjects);
                });
    }
}
