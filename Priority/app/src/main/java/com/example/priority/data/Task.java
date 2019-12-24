package com.example.priority.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

import java.util.UUID;

@Entity(tableName = "tasks")
public final class Task {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entryid")
    private final String mId;

    @NonNull
    @ColumnInfo(name = "title")
    private final String mTitle;

    @NonNull
    @ColumnInfo(name = "description")
    private final String mDescription;

    @ColumnInfo(name = "completed")
    private final boolean mCompleted;

    @Ignore
    public Task(@NonNull String title, @NonNull String description) {
        this(UUID.randomUUID().toString(), title, description, false);
    }

    @Ignore
    public Task(@NonNull String id, @NonNull String title, @NonNull String description) {
        this(id, title, description, false);
    }

    @Ignore
    public Task(@NonNull String title, @NonNull String description, boolean completed) {
        this(UUID.randomUUID().toString(), title, description, completed);
    }

    public Task(@NonNull String id, @NonNull String title, @NonNull String description, boolean completed) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mCompleted = completed;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    @NonNull
    public String getDescription() {
        return mDescription;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public boolean isActive() {
        return !mCompleted;
    }

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(mTitle) && Strings.isNullOrEmpty(mDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equal(getId(), task.getId()) &&
                Objects.equal(getTitle(), task.getTitle()) &&
                Objects.equal(getDescription(), task.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getTitle(), getDescription());
    }

    @Override
    public String toString() {
        return "Task{" +
                "mTitle='" + mTitle + '\'' +
                '}';
    }
}
