package com.example.priority.data.source.remote;

import android.os.Handler;

import androidx.annotation.NonNull;

import com.example.priority.data.Task;
import com.example.priority.data.TasksDataSource;
import com.google.common.collect.Lists;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class TasksRemoteDataSource implements TasksDataSource {

    private static TasksRemoteDataSource INSTANCE;

    private static final int SERVICE_LATENCY_IN_MILLIS = 0;


    private static final Map<String, Task> TASKS_SERVICE_DATA;

    static {

        TASKS_SERVICE_DATA = new LinkedHashMap<>(18);
        addTask("1. Build tower in Pisa", "Ground looks good, no foundation work required.");
        addTask("2. Finish bridge in Tacoma", "Found awesome girders at half the cost!");
        addTask("3. Build tower in Pisa", "Ground looks good, no foundation work required.");
        addTask("4. Finish bridge in Tacoma", "Found awesome girders at half the cost!");
        addTask("5. Build tower in Pisa", "Ground looks good, no foundation work required.");
        addTask("6. Finish bridge in Tacoma", "Found awesome girders at half the cost!");
        addTask("7. Build tower in Pisa", "Ground looks good, no foundation work required.");
        addTask("8. Finish bridge in Tacoma", "Found awesome girders at half the cost!");
        addTask("9. Build tower in Pisa", "Ground looks good, no foundation work required.");
        addTask("10. Finish bridge in Tacoma", "Found awesome girders at half the cost!");
        addTask("11. Build tower in Pisa", "Ground looks good, no foundation work required.");
        addTask("12. Finish bridge in Tacoma", "Found awesome girders at half the cost!");
        addTask("13. Build tower in Pisa", "Ground looks good, no foundation work required.");
        addTask("14. Finish bridge in Tacoma", "Found awesome girders at half the cost!");
        addTask("15. Build tower in Pisa", "Ground looks good, no foundation work required.");
        addTask("16. Finish bridge in Tacoma", "Found awesome girders at half the cost!");
        addTask("17. Build tower in Pisa", "Ground looks good, no foundation work required.");
        addTask("18.Finish bridge in Tacoma", "Found awesome girders at half the cost!");


    }

    public static TasksRemoteDataSource getINSTANCE() {

        if (INSTANCE == null) {
            INSTANCE = new TasksRemoteDataSource();
        }
        return INSTANCE;
    }

    private TasksRemoteDataSource() {}

    private static void addTask(String title, String description) {
        Task newTask = new Task(title, description);
        TASKS_SERVICE_DATA.put(newTask.getId(), newTask);
    }

    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onTasksLoaded(Lists.newArrayList(TASKS_SERVICE_DATA.values()));
            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {

    }

    @Override
    public void saveTask(@NonNull Task task) {

    }

    @Override
    public void completeTask(@NonNull Task task) {

        Task completedTask = new Task(task.getTitle(), task.getDescription(), task.getId(), true);
        TASKS_SERVICE_DATA.put(task.getId(), completedTask);
    }

    @Override
    public void completeTask(@NonNull String taskId) {

    }

    @Override
    public void activateTask(@NonNull Task task) {

    }

    @Override
    public void activateTask(@NonNull String taskId) {

    }

    @Override
    public void clearCompletedTasks() {

    }

    @Override
    public void refreshTasks() {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(@NonNull String taskId) {

    }
}
