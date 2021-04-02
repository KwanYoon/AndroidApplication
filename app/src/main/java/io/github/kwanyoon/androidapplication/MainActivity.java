package io.github.kwanyoon.androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.kwanyoon.androidapplication.adapter.ToDoAdapter;
import io.github.kwanyoon.androidapplication.model.ToDoModel;

public class MainActivity extends AppCompatActivity {

    // Recycler view
    private RecyclerView tasksRexyclerView;
    // ToDoAdapter
    private ToDoAdapter tasksAdapter;

    private List<ToDoModel> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hides topmost action bar
        getSupportActionBar().hide();

        taskList = new ArrayList<>();

        // finding and set it to linear layout
        tasksRexyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRexyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(this);
        tasksRexyclerView.setAdapter(tasksAdapter);

        // dummy data
        ToDoModel task = new ToDoModel();
        task.setTask("This is a test task");
        task.setStatus(0);
        task.setId(1);

        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);

        tasksAdapter.setTasks(taskList);
    }
}