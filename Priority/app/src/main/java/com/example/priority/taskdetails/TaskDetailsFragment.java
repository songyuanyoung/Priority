package com.example.priority.taskdetails;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.priority.R;
import com.example.priority.addedittask.AddEditTaskActivity;
import com.example.priority.addedittask.AddEditTaskFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskDetailsFragment extends Fragment implements TasksDetailsContract.View, CompoundButton.OnCheckedChangeListener {

    private static final String TAG = TaskDetailsFragment.class.getSimpleName();

    private static final String ARGUMENT_TASK_ID = "task_id";

    private CheckBox mTaskCompletedCheckBox;

    private TextView mTaskTitleTextView;

    private TextView mTaskDescriptionTextView;


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

        mTaskTitleTextView = root.findViewById(R.id.task_detail_title);
        mTaskDescriptionTextView = root.findViewById(R.id.task_detail_description);
        mTaskCompletedCheckBox = root.findViewById(R.id.task_detail_complete);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_edit_task);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.editTask();
            }
        });


        mTaskCompletedCheckBox.setOnCheckedChangeListener(this);

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
                mPresenter.deleteTask();
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
        mTaskTitleTextView.setText(title);
    }

    @Override
    public void hideDescription() {
    }

    @Override
    public void showDescription(String description) {
        mTaskDescriptionTextView.setText(description);

    }

    @Override
    public void showCompletionStatus(boolean complete) {
        mTaskCompletedCheckBox.setChecked(complete);
    }

    @Override
    public void showEditTask(String taskId) {
        Intent intent = new Intent(getActivity(), AddEditTaskActivity.class);
        intent.putExtra(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID, taskId);
        startActivity(intent);

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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.task_detail_complete) {
            if (isChecked) {
                mPresenter.completeTask();
            } else {
                mPresenter.activatTask();
            }
        }
    }
}
