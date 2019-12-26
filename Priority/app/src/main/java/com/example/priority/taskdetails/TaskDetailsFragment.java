package com.example.priority.taskdetails;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.priority.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskDetailsFragment extends Fragment implements TasksDetailsContract.View {

    private static final String TAG = TaskDetailsFragment.class.getSimpleName();

    private static final String ARGUMENT_TASK_ID = "task_id";


    private TasksDetailsContract.Presenter mPresenter;

    public TaskDetailsFragment() {
        // Required empty public constructor
    }
    public static TaskDetailsFragment newInstance(String taskId) {
        TaskDetailsFragment fragment = new TaskDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_TASK_ID, taskId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_task_details, container, false);
        setHasOptionsMenu(true);


        return root;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.taskdetail_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:

                break;
        }

        return true;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showMissingTask() {

    }

    @Override
    public void hideTitle() {

    }

    @Override
    public void showTitle(String title) {

    }

    @Override
    public void hideDescription() {

    }

    @Override
    public void showDescription(String description) {

    }

    @Override
    public void showCompletionStatus(boolean complete) {

    }

    @Override
    public void showEditTask(String taskId) {

    }

    @Override
    public void showTaskDeleted() {

    }

    @Override
    public void showTaskMarkedCompleted() {

    }

    @Override
    public void showTaskmarkedActive() {

    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setPresenter(TasksDetailsContract.Presenter presenter) {
        mPresenter = presenter;

    }
}
