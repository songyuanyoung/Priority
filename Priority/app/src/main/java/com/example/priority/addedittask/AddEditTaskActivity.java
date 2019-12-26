package com.example.priority.addedittask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.priority.R;

public class AddEditTaskActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_TASK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);
    }
}
