package com.example.priority.addedittask;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;


import com.example.priority.Injection;
import com.example.priority.R;
import com.example.priority.utils.ActivityUtils;

public class AddEditTaskActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_TASK = 1;

    private ActionBar mActionBar;

    private AddEditTaskContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);


        AddEditTaskFragment addEditTaskFragment = (AddEditTaskFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);


        String taskId = getIntent().getStringExtra(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID);
        setToolbarTitle(taskId);

        if (addEditTaskFragment == null) {
            addEditTaskFragment = AddEditTaskFragment.newInstance();

            if (taskId != null) {
                Bundle bundle = new Bundle();
                bundle.putString(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID, taskId);
                addEditTaskFragment.setArguments(bundle);
            }

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditTaskFragment, R.id.contentFrame);
        }


        mPresenter = new AddEditTaskPresenter(taskId, Injection.provideTasksRepository(getApplicationContext()), addEditTaskFragment, true);

    }

    private void setToolbarTitle(String taskId) {
        if (taskId == null) {
            mActionBar.setTitle(getString(R.string.add_task));
        } else {
            mActionBar.setTitle(getString(R.string.edit_task));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
