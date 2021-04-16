package io.github.kwanyoon.androidapplication.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.kwanyoon.androidapplication.AddNewSecret;
import io.github.kwanyoon.androidapplication.MainActivity;
import io.github.kwanyoon.androidapplication.R;
import io.github.kwanyoon.androidapplication.SecretActivity;
import io.github.kwanyoon.androidapplication.model.SecretModel;
import io.github.kwanyoon.androidapplication.utils.DatabaseHandler;

public class SecretAdapter extends RecyclerView.Adapter<SecretAdapter.ViewHolder> {
    private List<SecretModel> secretList;
    private SecretActivity activity;
    private DatabaseHandler db;

    public SecretAdapter(DatabaseHandler db, SecretActivity activity) {
        this.db = db;
        this.activity = activity;
    }

    @NonNull
    @Override
    public SecretAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View secretView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.secret_layout, parent, false);
        return new ViewHolder(secretView);
    }

    @Override
    public void onBindViewHolder(@NonNull SecretAdapter.ViewHolder holder, int position) {
        db.openDatabase();

        SecretModel item = secretList.get(position);
        holder.secret.setText(item.getPlaceholder());
        holder.secret.setChecked(toBoolean(item.getSecretStatus()));
        holder.secret.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.secret.setText(item.getSecret());
                } else {
                    holder.secret.setText(item.getPlaceholder());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return secretList.size();
    }

    public void setSecrets(List<SecretModel> secretList) {
        this.secretList = secretList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        SecretModel item = secretList.get(position);
        db.deleteSecret(item.getSecretId());
        secretList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position) {
        SecretModel item = secretList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getSecretId());
        bundle.putString("placeholder", item.getPlaceholder());
        bundle.putString("secret", item.getSecret());
        AddNewSecret fragment = new AddNewSecret();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewSecret.TAG);
    }

    public Context getContext() {
        return activity;
    }

    public static boolean toBoolean(int num) {
        return num != 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox secret;

        ViewHolder(View view) {
            super(view);
            secret = view.findViewById(R.id.secretCheckBox);
        }
    }
}
