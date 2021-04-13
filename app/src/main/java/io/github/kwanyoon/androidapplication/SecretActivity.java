package io.github.kwanyoon.androidapplication;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        secretList = db.getAllSecrets();
        Collections.reverse(secretList);
        secretAdapter.setSecrets(secretList);
        secretAdapter.notifyDataSetChanged();
    }
}
