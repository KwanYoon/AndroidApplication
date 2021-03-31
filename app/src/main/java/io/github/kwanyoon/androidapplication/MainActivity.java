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
        EditText firstNameEdit = findViewById(R.id.firstNameInput);
        String firstName = firstNameEdit.getText().toString();

        EditText lastNameEdit = findViewById(R.id.lastNameInput);
        String lastName = lastNameEdit.getText().toString();

        EditText emailEdit = findViewById(R.id.emailInput);
        String email = emailEdit.getText().toString();

        // changing text on button click
        TextView txtFirstName = findViewById(R.id.firstNameText);
        txtFirstName.setText("FIrst Name: " + firstName);

        TextView txtLastName = findViewById(R.id.lastNameText);
        txtLastName.setText("Last Name: " + lastName);

        TextView txtEmail = findViewById(R.id.emailText);
        txtEmail.setText("Email: " + email);
    }
}