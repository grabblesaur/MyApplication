package ru.bayar.bogdanov.myapplication.ui.cards;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.bayar.bogdanov.myapplication.Application;
import ru.bayar.bogdanov.myapplication.R;
import ru.bayar.bogdanov.myapplication.api.model.Comment;
import ru.bayar.bogdanov.myapplication.api.model.Post;
import ru.bayar.bogdanov.myapplication.api.model.TodoObject;
import ru.bayar.bogdanov.myapplication.api.model.User;

public class CardsFragment extends Fragment implements CardsView {

    private static final String TAG = CardsFragment.class.getName();

    // post
    @BindView(R.id.fc_post_id_et)
    EditText mPostIdEditText;
    @BindView(R.id.fc_post_id_btn)
    Button mPostIdButton;
    @BindView(R.id.fc_post_title_tv)
    TextView mPostTitleTextView;
    @BindView(R.id.fc_post_body_tv)
    TextView mPostBodyTextView;

    // comment
    @BindView(R.id.fc_comment_id_et)
    EditText mCommentIdEditText;
    @BindView(R.id.fc_comment_id_btn)
    Button mCommentIdButton;
    @BindView(R.id.fc_comment_name_tv)
    TextView mCommentNameTextView;
    @BindView(R.id.fc_comment_email_tv)
    TextView mCommentEmailTextView;
    @BindView(R.id.fc_comment_body_tv)
    TextView mCommentBodyTextView;

    // users
    @BindView(R.id.fc_users_message_tv)
    TextView mUsersMessageTextView;
    @BindView(R.id.fc_users_recycler_view)
    RecyclerView mUsersRecyclerView;

    // photo
    @BindView(R.id.fc_photo_iv)
    ImageView mImageView;

    // todos
    @BindView(R.id.fc_todos_et)
    EditText mTodosEditText;
    @BindView(R.id.fc_todos_btn)
    Button mTodosButton;
    @BindView(R.id.fc_todos_recycler_view)
    RecyclerView mTodosRecyclerView;

    @Inject
    CardsPresenter mPresenter;

    private Unbinder mUnbinder;
    private UsersAdapter mUsersAdapter;
    private TodosAdapter mTodosAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        Application.getComponent(getActivity()).inject(this);
        mPresenter.attachView(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (mPresenter != null && mPresenter.isViewAttached()) {
            mPresenter.detachView();
        }
    }

    private void initViews() {
        mPostIdButton.setOnClickListener(v -> {
            mPostIdEditText.setError(null);
            String numberString = mPostIdEditText.getText().toString();
            if (!numberString.isEmpty() && Integer.valueOf(numberString) > 0 && Integer.valueOf(numberString) <= 100) {
                mPresenter.getPost(Integer.valueOf(numberString));
            } else {
                mPostIdEditText.setError(getString(R.string.wrong_data));
            }
        });

        mCommentIdButton.setOnClickListener(v -> {
            mCommentIdEditText.setError(null);
            String numberString = mCommentIdEditText.getText().toString();
            if (!numberString.isEmpty() && Integer.valueOf(numberString) > 0 && Integer.valueOf(numberString) <= 500) {
                mPresenter.getComment(Integer.valueOf(numberString));
            } else {
                mCommentIdEditText.setError(getString(R.string.wrong_data));
            }
        });

        mUsersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        mUsersAdapter = new UsersAdapter(null);
        mUsersRecyclerView.setAdapter(mUsersAdapter);
        mPresenter.getUserList();
        mPresenter.getPhoto(3);

        mTodosButton.setOnClickListener(v -> {
            mTodosEditText.setError(null);
            String idString = mTodosEditText.getText().toString();
            if (!idString.isEmpty() && Integer.valueOf(idString) > 0 && Integer.valueOf(idString) <= 10) {
                mPresenter.getTodoList(Integer.valueOf(idString));
            } else {
                mTodosEditText.setError(getString(R.string.wrong_data));
            }
        });
    }

    @Override
    public void onSpecificPostGetSuccess(Post post) {
        mPostTitleTextView.setVisibility(View.VISIBLE);
        mPostBodyTextView.setVisibility(View.VISIBLE);
        mPostTitleTextView.setText(post.getTitle());
        mPostBodyTextView.setText(post.getBody());
    }

    @Override
    public void onSpecificPostGetError(Throwable throwable) {
        mPostTitleTextView.setVisibility(View.VISIBLE);
        mPostBodyTextView.setVisibility(View.GONE);
        mPostTitleTextView.setText(throwable.getMessage());
    }

    @Override
    public void onSpecificCommentGetSuccess(Comment comment) {
        mCommentNameTextView.setVisibility(View.VISIBLE);
        mCommentEmailTextView.setVisibility(View.VISIBLE);
        mCommentBodyTextView.setVisibility(View.VISIBLE);
        mCommentNameTextView.setText(comment.getName());
        mCommentEmailTextView.setText(comment.getEmail());
        mCommentBodyTextView.setText(comment.getBody());
    }

    @Override
    public void onSpecificCommentGetError(Throwable throwable) {
        mCommentNameTextView.setVisibility(View.VISIBLE);
        mCommentEmailTextView.setVisibility(View.GONE);
        mCommentBodyTextView.setVisibility(View.GONE);
        mCommentNameTextView.setText(throwable.getMessage());
    }

    @Override
    public void onUserGetSuccess(User user) {
        mUsersMessageTextView.setVisibility(View.GONE);
        mUsersAdapter.addUsers(user);
    }

    @Override
    public void onGetUrlSuccess(String url, String thumbnailUrl) {
        Log.i(TAG, "onGetUrlSuccess: url = " + url + ", thumbnail = " + thumbnailUrl);
        Glide.with(this)
                .load(thumbnailUrl)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(mImageView);
    }

    @Override
    public void onGetTodosListSuccess(int usersId, ArrayList<TodoObject> todoObjects) {
        Log.i(TAG, "onGetTodosListSuccess: objects.size() = " + todoObjects.size() + ", by usersId = " + usersId);
        mTodosRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mTodosRecyclerView.setAdapter(new TodosAdapter(todoObjects));
    }
}
