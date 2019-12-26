package com.example.priority.tasks;

import com.example.priority.data.Task;

public interface TaskItemListener {

    void onTaskClick(Task clickedTask);

    void onCompleteTaskClick(Task completedTask);

    void onActivateTaskClick(Task activatedTask);
}
