package com.example.priority.tasks;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.priority.R;
import com.example.priority.data.Task;

import java.util.List;

import timber.log.Timber;

import static com.google.common.base.Preconditions.checkNotNull;


public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    private List<Task> mTasks;
    private TaskItemListener mTaskItemListener;

    public TasksAdapter(List<Task> tasks, TaskItemListener taskItemListener) {
        setList(tasks);
        mTaskItemListener = taskItemListener;
    }

    public void replaceData(List<Task> tasks) {
        setList(tasks);
        notifyDataSetChanged();
    }

    private void setList(List<Task> tasks) {
        mTasks = checkNotNull(tasks);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Task task = mTasks.get(position);

        Timber.d(task.getTitle() + ":title");


        holder.mCompleteCheckBox.setChecked(task.isCompleted());
        holder.mTitleTextView.setText(task.getTitle());

        holder.mCompleteCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!task.isCompleted()) {
                    mTaskItemListener.onCompleteTaskClick(task);
                } else {
                    mTaskItemListener.onActivateTaskClick(task);
                }
            }
        });

        holder.mTaskItemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTaskItemListener.onTaskClick(task);
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTextView;
        private CheckBox mCompleteCheckBox;

        private LinearLayout mTaskItemLinearLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTaskItemLinearLayout = itemView.findViewById(R.id.task_item_layout);
            mTitleTextView = itemView.findViewById(R.id.title);
            mCompleteCheckBox = itemView.findViewById(R.id.complete);

        }
    }
}
