package com.example.priority.tasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.priority.R;
import com.example.priority.data.Task;
import com.example.priority.databinding.TaskItemBinding;

import java.util.List;

import timber.log.Timber;

import static com.google.common.base.Preconditions.checkNotNull;


public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> implements TaskItemListener {

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

        TaskItemBinding taskItemBinding = TaskItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(taskItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Task task = mTasks.get(position);

        Timber.d(task.getTitle() + ":title");
        holder.bind(task);
        holder.mViewDataBinding.setListener(this);


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    @Override
    public void onTaskClick(Task clickedTask) {
        mTaskItemListener.onTaskClick(clickedTask);
    }

    @Override
    public void onCompleteTaskClick(Task completedTask) {

    }

    @Override
    public void onActivateTaskClick(Task activatedTask) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TaskItemBinding mViewDataBinding;
        public ViewHolder(TaskItemBinding taskItemBinding) {
            super(taskItemBinding.getRoot());
            this.mViewDataBinding = taskItemBinding;
        }

        public void bind(Task task) {
            mViewDataBinding.setTask(task);
        }
    }
}
