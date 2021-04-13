package io.github.kwanyoon.androidapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.kwanyoon.androidapplication.adapter.SecretAdapter;
import io.github.kwanyoon.androidapplication.adapter.ToDoAdapter;
import io.github.kwanyoon.androidapplication.model.SecretModel;
import io.github.kwanyoon.androidapplication.utils.DatabaseHandler;

public class SecretActivity extends AppCompatActivity implements DialogCloseListener {

    // Recycler view
    private RecyclerView secretRecyclerView;
    // ToDoAdapter
    private SecretAdapter secretAdapter;
    private FloatingActionButton newSecretFab, mainFab;

    private List<SecretModel> secretList;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("this works!");
        setContentView(R.layout.activity_secret);

        // hides topmost action bar
        getSupportActionBar().hide();

        // database variables
        db = new DatabaseHandler(this);
        db.openDatabase();

        secretList = new ArrayList<>();

        // finding and set it to linear layout
        secretRecyclerView = findViewById(R.id.secretsRecyclerView);
        secretRecyclerView.setLayoutManager((new LinearLayoutManager(this)));
        secretAdapter = new SecretAdapter(db, this);
        secretRecyclerView.setAdapter(secretAdapter);

        newSecretFab = findViewById(R.id.newSecretFab);

        // swipe functions
        //ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(secretAdapter));
        //itemTouchHelper.attachToRecyclerView(secretRecyclerView);

        secretList = db.getAllSecrets();
        Collections.reverse(secretList);
        secretAdapter.setSecrets(secretList);

        newSecretFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewSecret.newInstance().show(getSupportFragmentManager(), AddNewSecret.TAG);
            }
        });

        // main button
        mainFab = findViewById(R.id.mainFab);
        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(mainIntent, 0);
            }
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        secretList = db.getAllSecrets();
        Collections.reverse(secretList);
        secretAdapter.setSecrets(secretList);
        secretAdapter.notifyDataSetChanged();
    }
}
