package com.example.priority.addedittask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.priority.data.Task;
import com.example.priority.data.source.TasksRepository;

public class AddEditTaskPresenter implements AddEditTaskContract.Presenter {

    @NonNull
    private final TasksRepository mTasksRepository;

    @NonNull
    private final AddEditTaskContract.View mView;

    @Nullable
    private String mTaskId;

    private boolean isDataMissing;

    public AddEditTaskPresenter(@Nullable String taskId, @NonNull TasksRepository tasksRepository, @NonNull AddEditTaskContract.View view, boolean shouldLoadDataFromRepo) {
        mTaskId = taskId;
        mTasksRepository = tasksRepository;
        mView = view;
        this.isDataMissing = shouldLoadDataFromRepo;

        mView.setPresenter(this);
    }

    @Override
    public void saveTask(String title, String description) {
        Task newTask = new Task(title, description);
        mTasksRepository.saveTask(newTask);
        mView.showTasksList();
    }

    @Override
    public void populateTask() {

    }

    @Override
    public boolean isDataMissing() {
        return false;
    }

    @Override
    public void start() {

    }
}
