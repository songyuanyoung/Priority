package com.example.priority.data.source.local;

import android.widget.ImageView;

import static com.google.common.base.Preconditions.checkNotNull;

import androidx.annotation.NonNull;

import com.example.priority.R;
import com.example.priority.data.Task;
import com.example.priority.data.TasksDataSource;
import com.example.priority.utils.AppExecutors;

import java.util.List;

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
    public void getTasks(@NonNull final LoadTasksCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Task> tasks = mTasksDao.getTasks();
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (tasks.isEmpty()) {
                            callback.onDataNotAvailable();
                        } else {
                            callback.onTasksLoaded(tasks);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);

    }

    @Override
    public void getTask(@NonNull final String taskId, @NonNull final GetTaskCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Task task = mTasksDao.getTask(taskId);
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (task == null) {
                            callback.onTaskNotAvailable();
                        } else {
                            callback.onTaskLoaded(task);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void saveTask(@NonNull final Task task) {
        checkNotNull(task);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mTasksDao.insertTask(task);
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);

    }

    @Override
    public void completeTask(@NonNull final Task task) {
        checkNotNull(task);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mTasksDao.updateCompleted(task.getId(), true);
            }
        };

        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void completeTask(@NonNull final String taskId) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mTasksDao.updateCompleted(taskId, true);
            }
        };

        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void activateTask(@NonNull final Task task) {
        checkNotNull(task);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mTasksDao.updateCompleted(task.getId(), false);
            }
        };

        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void activateTask(@NonNull final String taskId) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mTasksDao.updateCompleted(taskId, false);
            }
        };

        mAppExecutors.getDiskIO().execute(runnable);
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
    public void deleteTask(@NonNull final String taskId) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mTasksDao.deleteTaskById(taskId);
            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }
}
