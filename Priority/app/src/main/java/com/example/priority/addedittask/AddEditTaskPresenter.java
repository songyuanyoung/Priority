package com.example.priority.addedittask;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.priority.data.Task;
import com.example.priority.data.TasksDataSource;
import com.example.priority.data.source.TasksRepository;

public class AddEditTaskPresenter implements AddEditTaskContract.Presenter, TasksDataSource.GetTaskCallback {

    private static final String TAG = AddEditTaskPresenter.class.getSimpleName();

    @NonNull
    private final TasksRepository mTasksRepository;

    @NonNull
    private final AddEditTaskContract.View mView;

    @Nullable
    private String mTaskId;

    private boolean mIsDataMissing;

    public AddEditTaskPresenter(@Nullable String taskId, @NonNull TasksRepository tasksRepository, @NonNull AddEditTaskContract.View view, boolean shouldLoadDataFromRepo) {
        mTaskId = taskId;
        mTasksRepository = tasksRepository;
        mView = view;
        this.mIsDataMissing = shouldLoadDataFromRepo;

        mView.setPresenter(this);
    }

    @Override
    public void saveTask(String title, String description) {

        if (isNewTask()) {
            createNewTask(title, description);
        } else {
            updateTask(title, description);
        }

    }

    @Override
    public void populateTask() {
        mTasksRepository.getTask(mTaskId, this);

    }

    @Override
    public boolean isDataMissing() {
        return false;
    }

    @Override
    public void start() {
        if (!isNewTask() && !mIsDataMissing) {
            populateTask();
        }
    }

    private boolean isNewTask() {
        return mTaskId == null;
    }

    @Override
    public void onTaskLoaded(Task task) {

        Log.d(TAG, "task: " + task.toString());
        mView.setTitle(task.getTitle());
        mView.setDescription(task.getDescription());
    }

    @Override
    public void onTaskNotAvailable() {
        mView.showEmptyTaskError();
    }


    private void createNewTask(String title, String description) {
        Task newTask = new Task(title, description);
        if (newTask.isEmpty()) {
            mView.showEmptyTaskError();
        } else{
            mTasksRepository.saveTask(newTask);
            mView.showTasksList();
        }
    }

    private void updateTask(String title, String description) {
        if (isNewTask()) {
            throw  new RuntimeException("Can't be new task");
        }

        mTasksRepository.saveTask(new Task(mTaskId, title, description));
    }
}
