package com.example.priority.taskdetails;

import com.example.priority.BasePresenter;
import com.example.priority.BaseView;

public interface TasksDetailsContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);

        void showMissingTask();

        void hideTitle();

        void showTitle(String title);

        void hideDescription();

        void showDescription(String description);

        void showCompletionStatus(boolean complete);

        void showEditTask(String taskId);

        void showTaskDeleted();

        void showTaskMarkedCompleted();

        void showTaskmarkedActive();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void editTask();

        void deleteTask();

        void completeTask();

        void activatTask();
    }
}
