package io.github.kwanyoon.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onBtnClick(View view) {
        // getting user input
        EditText editTxtName = findViewById(R.id.editTxtName);
        String nameInput = editTxtName.getText().toString();

        // changing text on button click
        TextView txtHello = findViewById(R.id.textView);
        txtHello.setText("Hello " + nameInput);
    }
}