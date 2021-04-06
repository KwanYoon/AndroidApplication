package io.github.kwanyoon.androidapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import io.github.kwanyoon.androidapplication.model.SecretModel;
import io.github.kwanyoon.androidapplication.utils.DatabaseHandler;

public class AddNewSecret extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";

    private EditText newPlaceholderText, newSecretText;
    private Button newSecretSaveButton;
    private DatabaseHandler db;

    public static AddNewSecret newInstance() { return new AddNewSecret(); }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.Dialogstyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_secret, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newPlaceholderText = getView().findViewById(R.id.newSecretPlaceholder);
        newSecretText = getView().findViewById(R.id.newSecretSecret);
        newSecretSaveButton = getView().findViewById(R.id.newSecretButton);

        db = new DatabaseHandler(getActivity());
        db.openDatabase();

        boolean isUpdate = false;
        final Bundle bundle = getArguments();

        // if update instead of create
        if (bundle != null) {
            isUpdate = true;
            String placeholder = bundle.getString("placeholder");
            String secret = bundle.getString("secret");
            newPlaceholderText.setText(placeholder);
            newSecretText.setText(secret);
            if (placeholder.length() > 0) {
                newSecretSaveButton.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            }
        }

        // text change checks
        newSecretText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // checking if task is empty or not
                if (s.toString().equals("")) {
                    newSecretSaveButton.setEnabled(false);
                    newSecretSaveButton.setTextColor(Color.GRAY);
                } else {
                    newSecretSaveButton.setEnabled(true);
                    newSecretSaveButton.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        // button click listener
        boolean finalIsUpdate = isUpdate;
        newSecretSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placeholder = newPlaceholderText.getText().toString();
                String secretText = newSecretText.getText().toString();
                if (finalIsUpdate) {
                    // if update
                    db.updateSecret(bundle.getInt("id"), placeholder, secretText);
                } else {
                    // if create
                    SecretModel secret = new SecretModel();
                    secret.setPlaceholder(placeholder);
                    secret.setSecret(secretText);
                    secret.setSecretStatus(0);
                    db.insertSecret(secret);
                }
                // dismiss bottom sheet
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Activity activity = getActivity();
        if (activity instanceof DialogCloseListener) {
            ((DialogCloseListener)activity).handleDialogClose(dialog);
        }
    }
}
