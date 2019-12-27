package com.example.priority.tasks;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.priority.R;
import com.example.priority.addedittask.AddEditTaskActivity;
import com.example.priority.data.Task;
import com.example.priority.taskdetails.TaskDetailsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TasksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TasksFragment extends Fragment implements TasksContract.View {

    private final static String TAG = TasksFragment.class.getSimpleName();

    private TasksAdapter mListAdapter;


    private TasksContract.Presenter mTasksPresenter;


    public TasksFragment() {
        // Required empty public constructor
    }


    public static TasksFragment newInstance() {
        TasksFragment fragment = new TasksFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new TasksAdapter(new ArrayList<Task>(0), mTaskItemListener);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);

        ListView listView = root.findViewById(R.id.tasks_list);
        listView.setAdapter(mListAdapter);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_add_task);

        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTasksPresenter.addNewTask();
            }
        });


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTasksPresenter.start();
    }

    TaskItemListener mTaskItemListener = new TaskItemListener() {
        @Override
        public void onTaskClick(Task clickedTask) {
            mTasksPresenter.openTaskDetails(clickedTask);
        }

        @Override
        public void onCompleteTaskClick(Task completedTask) {
            mTasksPresenter.completeTask(completedTask);
        }

        @Override
        public void onActivateTaskClick(Task activatedTask) {
            mTasksPresenter.activateTask(activatedTask);
        }
    };

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showTasks(List<Task> tasks) {
        mListAdapter.replaceData(tasks);

    }

    @Override
    public void showAddTask() {
        Intent intent = new Intent(getActivity(), AddEditTaskActivity.class);
        startActivityForResult(intent, AddEditTaskActivity.REQUEST_ADD_TASK);
    }

    @Override
    public void showTaskDetailsUi(String taskId) {
        Log.d(TAG, "taskId:" + taskId);
        Intent intent = new Intent(getActivity(), TaskDetailsActivity.class);
        intent.putExtra(TaskDetailsActivity.EXTRA_TASK_ID, taskId);
        startActivity(intent);
    }

    @Override
    public void showTaskMarkedComplete() {
        showMessage(getString(R.string.task_marked_complete));
    }

    @Override
    public void showTaskMarkedActive() {

    }

    @Override
    public void showCompletedTasksCleared() {

    }

    @Override
    public void showLoadinhTasksError() {

    }

    @Override
    public void showNoTasks() {

    }

    @Override
    public void showActiveFilterLabel() {

    }

    @Override
    public void showCompletedFilterLabel() {

    }

    @Override
    public void showAllFilterLabel() {

    }

    @Override
    public void showNoActiveTasks() {

    }

    @Override
    public void showNoCompletedTasks() {

    }

    @Override
    public void showSuccessfullySavedMessage() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showFilteringPopUpMenu() {

    }

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        mTasksPresenter = checkNotNull(presenter);
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }
}
