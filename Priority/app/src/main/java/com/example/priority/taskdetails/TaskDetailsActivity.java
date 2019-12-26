package com.example.priority.taskdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.priority.R;
import com.example.priority.utils.ActivityUtils;

public class TaskDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        TaskDetailsFragment taskDetailsFragment = (TaskDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (taskDetailsFragment == null) {
            taskDetailsFragment = TaskDetailsFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), taskDetailsFragment, R.id.contentFrame);
        }

    }
}
