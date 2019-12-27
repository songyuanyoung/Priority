package com.example.priority.addedittask;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.priority.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class AddEditTaskFragment extends Fragment implements AddEditTaskContract.View {

    private static final String TAG = AddEditTaskFragment.class.getSimpleName();

    public static final String ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID";

    private EditText mTitle;

    private EditText mDescription;

    private AddEditTaskContract.Presenter mPresenter;

    public AddEditTaskFragment() {
        // Required empty public constructor
    }

    public static AddEditTaskFragment newInstance() {
        AddEditTaskFragment fragment = new AddEditTaskFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_edit_task_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "on save");
                mPresenter.saveTask(mTitle.getText().toString(), mDescription.getText().toString());
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View root = inflater.inflate(R.layout.fragment_add_edit_task, container, false);

        mTitle = root.findViewById(R.id.add_task_title);
        mDescription = root.findViewById(R.id.add_task_description);


        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showEmptyTaskError() {

    }

    @Override
    public void showTasksList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void setDescription(String description) {
        mDescription.setText(description);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(AddEditTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
