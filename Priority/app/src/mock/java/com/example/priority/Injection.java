package com.example.priority;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.priority.data.source.TasksRepository;
import com.example.priority.data.source.local.TasksLocalDataSource;
import com.example.priority.data.source.local.ToDoDatabase;
import com.example.priority.data.source.remote.TasksRemoteDataSource;
import com.example.priority.utils.AppExecutors;


import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {

    public static TasksRepository provideTasksRepository(@NonNull Context context) {
        checkNotNull(context);
        ToDoDatabase doDatabase = ToDoDatabase.getInstance(context);
        return TasksRepository.getINSTANCE(TasksRemoteDataSource.getINSTANCE(), TasksLocalDataSource.getInstance(new AppExecutors(), doDatabase.mTasksDao()));

    }
}
