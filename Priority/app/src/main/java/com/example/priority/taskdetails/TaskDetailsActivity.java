package com.example.priority.taskdetails;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;

import com.example.priority.Injection;
import com.example.priority.R;
import com.example.priority.utils.ActivityUtils;

public class TaskDetailsActivity extends AppCompatActivity {

    private final static String TAG = TaskDetailsActivity.class.getSimpleName();

    public static final String EXTRA_TASK_ID = "task_id";

    private TasksDetailsContract.Presenter mPresenter;

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        String taskId = getIntent().getStringExtra(EXTRA_TASK_ID);



        TaskDetailsFragment taskDetailsFragment = (TaskDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (taskDetailsFragment == null) {
            taskDetailsFragment = TaskDetailsFragment.newInstance(taskId);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), taskDetailsFragment, R.id.contentFrame);
        }


        mPresenter = new TaskDetailPresenter(taskId, Injection.provideTasksRepository(getApplicationContext()), taskDetailsFragment);
    }
}
