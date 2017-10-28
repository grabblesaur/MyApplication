package ru.bayar.bogdanov.myapplication.ui.cards;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.bayar.bogdanov.myapplication.R;
import ru.bayar.bogdanov.myapplication.api.model.TodoObject;

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.TodoViewHolder> {

    private ArrayList<TodoObject> mTodoObjects;

    public TodosAdapter(ArrayList<TodoObject> todoObjects) {
        mTodoObjects = todoObjects;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        holder.onBind(mTodoObjects.get(position));
    }

    @Override
    public int getItemCount() {
        return mTodoObjects.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_todo_title_tv)
        TextView mTitleTextView;
        @BindView(R.id.item_todo_is_completed_iv)
        ImageView mIsCompletedImageView;

        public TodoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(TodoObject todoObject) {
            mTitleTextView.setText(todoObject.getTitle());
            if (todoObject.isCompleted()) {
                mIsCompletedImageView.setImageResource(R.drawable.ic_done_24dp);
            }
        }
    }
}
