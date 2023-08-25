package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class mydatabasehelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "miniblog.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "mes_blogs";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "titre";
    private static final String COLUMN_CONTENU = "contenu";


    public mydatabasehelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_CONTENU + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addbook(String titre, String contenu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, titre);
        cv.put(COLUMN_CONTENU, contenu);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public List<Post> getAllPosts(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        List<Post> posts = new ArrayList<Post>();

        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Post p = new Post();
            p.setId((int) cursor.getLong(0));
            p.setTitre(cursor.getString(1));
            p.setContenu(cursor.getString(2));
            posts.add(p);
            cursor.moveToNext();
        }
        cursor.close();
        return posts;
    }


    public Post getPostById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        Post objet = new Post();

        try {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE _id = ?";
            String[] selectionArgs = {String.valueOf(id)};

            cursor = db.rawQuery(query, selectionArgs);

            if (cursor != null && cursor.moveToFirst()) {
                objet.setId(Math.toIntExact(id));
                objet.setTitre(cursor.getString(1));
                objet.setContenu(cursor.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return objet;
    }
}



