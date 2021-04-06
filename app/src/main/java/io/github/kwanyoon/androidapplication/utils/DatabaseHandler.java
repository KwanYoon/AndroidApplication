
package io.github.kwanyoon.androidapplication.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import io.github.kwanyoon.androidapplication.model.SecretModel;
import io.github.kwanyoon.androidapplication.model.ToDoModel;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "toDoListDatabase";

    // To-do-list table
    private static final String TODO_TABLE = "todo";
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String STATUS = "status";
    private static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE + "(" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK + " TEXT, " + STATUS + " INTEGER)";

    // Secret table
    private static final String SECRET_TABLE = "secret";
    private static final String SECRET_ID = "secret_id";
    private static final String PLACEHOLDER = "placeholder";
    private static final String SECRET = "secret";
    private static final String SECRET_STATUS = "secret_status";
    private static final String CREATE_SECRET_TABLE = "CREATE TABLE " + SECRET_TABLE + "(" +
            SECRET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PLACEHOLDER + " TEXT, " +
            SECRET + " TEXT," + SECRET_STATUS + " INTEGER" + ")";

    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TODO_TABLE);
        db.execSQL(CREATE_SECRET_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop the tables
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SECRET_TABLE);
        // Create table again
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public void insertTask(ToDoModel task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(STATUS, 0);
        db.insert(TODO_TABLE, null, cv);
    }

    public void insertSecret(SecretModel secret) {
        ContentValues cv = new ContentValues();
        cv.put(PLACEHOLDER, secret.getPlaceholder());
        cv.put(SECRET, secret.getSecret());
        cv.put(SECRET_STATUS, 0);
        db.insert(SECRET_TABLE, null, cv);
    }

    public List<ToDoModel> getAllTasks() {
        List<ToDoModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try {
            cur = db.query(TODO_TABLE, null, null, null, null, null, null);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    do {
                        ToDoModel task = new ToDoModel();
                        task.setId(cur.getInt(cur.getColumnIndex(ID)));
                        task.setTask(cur.getString(cur.getColumnIndex(TASK)));
                        task.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
                        taskList.add(task);
                    } while (cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            cur.close();
        }
        return taskList;
    }

    public List<SecretModel> getAllSecrets() {
        List<SecretModel> secretList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try {
            cur = db.query(SECRET_TABLE, null, null, null, null, null, null);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    // loop through entire table
                    do {
                        SecretModel secret = new SecretModel();
                        secret.setSecretId(cur.getInt(cur.getColumnIndex(SECRET_ID)));
                        secret.setPlaceholder(cur.getString(cur.getColumnIndex(PLACEHOLDER)));
                        secret.setSecret(cur.getString(cur.getColumnIndex(SECRET)));
                        secret.setSecretStatus(cur.getInt(cur.getColumnIndex(SECRET_STATUS)));
                        secretList.add(secret);
                    } while (cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            cur.close();
        }
        return secretList;
    }

    public void updateStatus(int id, int status) {
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv, ID + "=?", new String[] {String.valueOf(id)});
    }

    public void updateSecretStatus(int id, int status) {
        ContentValues cv = new ContentValues();
        cv.put(SECRET_STATUS, status);
        db.update(SECRET_TABLE, cv, SECRET_ID + "=?", new String[] {String.valueOf(id)});
    }

    public void updateTask (int id, String task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task);
        db.update(TODO_TABLE, cv, ID + "=?", new String[] {String.valueOf(id)});
    }

    public void updateSecret (int id, String placeholder, String secret) {
        ContentValues cv = new ContentValues();
        cv.put(PLACEHOLDER, placeholder);
        cv.put(SECRET, secret);
        db.update(SECRET_TABLE, cv, SECRET_ID + "=?", new String[] {String.valueOf(id)});
    }

    public void deleteTask(int id) {
        db.delete(TODO_TABLE, ID + "=?", new String[] {String.valueOf(id)});
    }

    public void deleteSecret(int id) {
        db.delete(SECRET_TABLE, SECRET_ID + "=?", new String[] {String.valueOf(id)});
    }
}
