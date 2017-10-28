package ru.bayar.bogdanov.myapplication.ui.cards;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.bayar.bogdanov.myapplication.R;
import ru.bayar.bogdanov.myapplication.api.model.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private ArrayList<User> mUserList;

    public UsersAdapter(ArrayList<User> userList) {
        if (userList == null) {
            mUserList = new ArrayList<>();
        } else {
            mUserList = userList;
        }
    }

    public void addUsers(List<User> userList) {
        mUserList.addAll(userList);
        notifyItemRangeInserted(0, userList.size());
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.onBind(mUserList.get(position));
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_user_name)
        TextView mUserName;
        @BindView(R.id.item_user_email)
        TextView mUserEmail;
        @BindView(R.id.item_user_phone)
        TextView mUserPhone;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(User user) {
            mUserName.setText(String.format("%s (%s)", user.getUsername(), user.getName()));
            mUserEmail.setText(user.getEmail());
            mUserPhone.setText(user.getPhone());
        }
    }
}
