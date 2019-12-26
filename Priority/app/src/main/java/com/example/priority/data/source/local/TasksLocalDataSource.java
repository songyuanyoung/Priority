package com.example.priority.data.source.local;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.priority.data.Task;
import com.example.priority.data.TasksDataSource;
import com.example.priority.utils.AppExecutors;

public class TasksLocalDataSource implements TasksDataSource {

    private static volatile TasksLocalDataSource INSTANCE;

    private AppExecutors mAppExecutors;

    private TasksDao mTasksDao;

    public TasksLocalDataSource(AppExecutors appExecutors, TasksDao tasksDao) {
        mAppExecutors = appExecutors;
        mTasksDao = tasksDao;
    }

    public static TasksLocalDataSource getInstance(@NonNull AppExecutors appExecutors, @NonNull TasksDao tasksDao) {

        if (INSTANCE == null) {
            synchronized (TasksLocalDataSource.class) {
                INSTANCE = new TasksLocalDataSource(appExecutors, tasksDao);

            }
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {

    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {

    }

    @Override
    public void saveTask(@NonNull Task task) {

    }

    @Override
    public void completeTask(@NonNull Task task) {

    }

    @Override
    public void completeTask(@NonNull String taskId) {

    }

    @Override
    public void activateTask(@NonNull Task task) {

    }

    @Override
    public void activateTask(@NonNull String taskId) {

    }

    @Override
    public void clearCompletedTasks() {

    }

    @Override
    public void refreshTasks() {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(@NonNull String taskId) {

    }
}
