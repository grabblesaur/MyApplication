package ru.bayar.bogdanov.myapplication.base;


public abstract class BasePresenter <V extends BaseView> {

    private V mView;

    public final void attachView(V view) {
        if (view == null) {
            throw new NullPointerException("View must not be null");
        }
        mView = view;
    }

    public final void detachView() {
        mView = null;
    }

    public final V getView() {
        return mView;
    }

    public final boolean isViewAttached() {
        return mView != null;
    }
}
