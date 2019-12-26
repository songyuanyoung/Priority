package com.example.priority.data.source;

import androidx.annotation.NonNull;

import com.example.priority.data.Task;
import com.example.priority.data.TasksDataSource;

import static com.google.common.base.Preconditions.checkNotNull;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TasksRepository implements TasksDataSource {

    private static TasksRepository INSTANCE;

    private final TasksDataSource mTasksRemoteDataSource;


    Map<String, Task> mCachedTasks;

    private final TasksDataSource mTasksLocalDataSource;


    public TasksRepository(@NonNull TasksDataSource tasksRemoteDataSource, @NonNull TasksDataSource tasksLocalDataSource) {
        mTasksRemoteDataSource = tasksRemoteDataSource;
        mTasksLocalDataSource = tasksLocalDataSource;
    }

    public static TasksRepository getINSTANCE(TasksDataSource tasksRemoteDataSource, TasksDataSource tasksLocalDataSource) {

        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }

        return INSTANCE;
    }

    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {
        mTasksLocalDataSource.getTasks(new LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                callback.onTasksLoaded(tasks);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull final GetTaskCallback callback) {

        mTasksLocalDataSource.getTask(taskId, new GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                callback.onTaskLoaded(task);
            }

            @Override
            public void onTaskNotAvailable() {
                callback.onTaskNotAvailable();
            }
        });
    }

    @Override
    public void saveTask(@NonNull Task task) {
        checkNotNull(task);
        mTasksRemoteDataSource.saveTask(task);
        mTasksLocalDataSource.saveTask(task);

        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }

        mCachedTasks.put(task.getId(), task);
    }

    @Override
    public void completeTask(@NonNull Task task) {
        mTasksRemoteDataSource.completeTask(task);
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
