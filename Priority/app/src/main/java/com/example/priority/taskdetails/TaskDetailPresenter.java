package com.example.priority.taskdetails;

import android.util.Log;

import com.example.priority.data.Task;
import com.example.priority.data.TasksDataSource;
import com.example.priority.data.source.TasksRepository;
import com.google.common.base.Strings;

public class TaskDetailPresenter implements TasksDetailsContract.Presenter {

    private final static String TAG = TaskDetailPresenter.class.getSimpleName();

    private final TasksRepository mTasksRepository;

    private final TasksDetailsContract.View mView;

    private String mTaskId;

    public TaskDetailPresenter(String taskId, TasksRepository tasksRepository, TasksDetailsContract.View view) {
        mTaskId = taskId;
        mTasksRepository = tasksRepository;
        mView = view;

        mView.setPresenter(this);
    }


    @Override
    public void editTask() {

    }

    @Override
    public void deleteTask() {

    }

    @Override
    public void completeTask() {

    }

    @Override
    public void activatTask() {

    }

    @Override
    public void start() {

        openTask();
    }

    private void openTask() {



        if (Strings.isNullOrEmpty(mTaskId)) {
            mView.showMissingTask();
            return;
        }
        Log.d(TAG, "taskId:" + mTaskId);
        mView.setLoadingIndicator(true);
        mTasksRepository.getTask(mTaskId, new TasksDataSource.GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                Log.d(TAG, task.toString());

                if (!mView.isActive()) {
                    return;
                }

                mView.setLoadingIndicator(false);
                if (null == task) {
                    mView.showMissingTask();
                } else {
                    showTask(task);
                }
            }

            @Override
            public void onTaskNotAvailable() {
                Log.d(TAG, "onTaskNotAvailable");

                if (!mView.isActive()) {
                    return;
                }

                mView.showMissingTask();
            }
        });
    }


    private void showTask(Task task) {

        Log.d(TAG, task.toString());
        String title = task.getTitle();
        String description = task.getDescription();

        if (Strings.isNullOrEmpty(title)) {
            mView.hideTitle();
        } else {
            mView.showTitle(title);
        }

        if (Strings.isNullOrEmpty(description)) {
            mView.hideDescription();
        } else {
            mView.showDescription(description);
        }

        mView.showCompletionStatus(task.isCompleted());
    }
}
