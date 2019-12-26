package com.example.priority;

import com.example.priority.data.source.TasksRepository;
import com.example.priority.data.source.local.TasksLocalDataSource;
import com.example.priority.data.source.remote.TasksRemoteDataSource;


import timber.log.Timber;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {

    public static TasksRepository provideTasksRepository() {
        Timber.d("provideTasksRepository");
        return TasksRepository.getINSTANCE(TasksRemoteDataSource.getINSTANCE(), TasksLocalDataSource.getINSTANCE());

    }
}
