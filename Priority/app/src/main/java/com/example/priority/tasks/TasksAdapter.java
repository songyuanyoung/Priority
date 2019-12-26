package com.example.priority.tasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.priority.R;
import com.example.priority.data.Task;

import java.util.List;
import static com.google.common.base.Preconditions.checkNotNull;


public class TasksAdapter extends BaseAdapter {

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

    @Override
    public int getCount() {
        return mTasks.size();
    }

    @Override
    public Task getItem(int position) {
        return mTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;

        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            rowView = inflater.inflate(R.layout.task_item, viewGroup, false);
        }

        final Task task = getItem(i);

        TextView titleTV = (TextView) rowView.findViewById(R.id.title);
        titleTV.setText(task.getTitle());

        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.complete);
        checkBox.setChecked(task.isCompleted());

        if (task.isCompleted()) {
            rowView.setBackgroundDrawable(viewGroup.getContext().getResources().getDrawable(R.drawable.list_completed_touch_feedback));
        } else {
            rowView.setBackgroundDrawable(viewGroup.getContext().getResources().getDrawable(R.drawable.touch_feedback));

        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!task.isCompleted()) {
                    mTaskItemListener.onCompleteTaskClick(task);
                } else {
                    mTaskItemListener.onActivateTaskClick(task);
                }
            }
        });

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTaskItemListener.onTaskClick(task);
            }
        });


        return rowView;
    }
}
