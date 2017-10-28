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
        mApiService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(5)
                .subscribe(users -> {
                    getView().onUserListGetSuccess(users);
                }, throwable -> {
                    getView().onUserListGetError(throwable);
                });
    }
}
