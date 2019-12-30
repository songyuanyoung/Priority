package com.example.priority.tasks;

import androidx.annotation.NonNull;

import com.example.priority.data.Task;
import com.example.priority.data.TasksDataSource;
import com.example.priority.data.source.TasksRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class TasksPresenter implements TasksContract.Presenter {

    String TAG = TasksPresenter.class.getSimpleName();

    private final TasksRepository mTasksRepository;

    private final TasksContract.View mTasksView;

    private boolean mFirstLoad = true;


    public TasksPresenter(@NonNull TasksRepository tasksRepository, @NonNull TasksContract.View tasksView) {
        mTasksRepository = checkNotNull(tasksRepository);
        mTasksView = checkNotNull(tasksView);

        mTasksView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadTasks(boolean forceUpdate) {
        loadTasks(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadTasks(boolean forcedUpdate, final boolean showLoadingUi) {
//        if (showLoadingUi) {
//            mTasksView.setLoadingIndicator(true);
//        }
//
//        if (forcedUpdate) {
//            mTasksRepository.refreshTasks();
//        }

        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                List<Task> tasksToShow = tasks;
                processTasks(tasksToShow);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    private void processTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            mTasksView.showNoTasks();
        } else {

            mTasksView.showTasks(tasks);
        }
    }

    @Override
    public void addNewTask() {
        mTasksView.showAddTask();
    }

    @Override
    public void openTaskDetails(@NonNull Task requestedTask) {
        mTasksView.showTaskDetailsUi(requestedTask.getId());

    }

    @Override
    public void completeTask(@NonNull Task completedTask) {
        checkNotNull(completedTask, "completedTask can't be null");
        mTasksRepository.completeTask(completedTask);
        mTasksView.showTaskMarkedComplete();
        loadTasks(false, false);

    }

    @Override
    public void activateTask(@NonNull Task activeTask) {

    }

    @Override
    public void clearCompletedTasks() {

    }

    @Override
    public void setFiltering(TasksFilterType requestType) {

    }

    @Override
    public TasksFilterType getFiltering() {
        return null;
    }

    @Override
    public void start() {
        loadTasks(false);
    }
}
