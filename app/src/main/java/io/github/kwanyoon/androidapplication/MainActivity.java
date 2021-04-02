package io.github.kwanyoon.androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView tasksRexyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hides topmost action bar
        getSupportActionBar().hide();

        tasksRexyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRexyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}