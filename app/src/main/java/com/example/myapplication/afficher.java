package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class afficher extends AppCompatActivity {
    FloatingActionButton back;
    mydatabasehelper mydb;
    TextView tt,cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher);

        mydb = new mydatabasehelper(afficher.this);


        Intent intent = getIntent();
        Long id = Long.valueOf(intent.getStringExtra("id"));
        Log.d("id recupere",id.toString());

        Post p = mydb.getPostById(id);
        back = findViewById(R.id.back);

        tt = findViewById(R.id.title);
        cc = findViewById(R.id.content);

        tt.setText(p.getTitre());
        cc.setText(p.getContenu());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(afficher.this, MainActivity.class);
                startActivity(backIntent);
            }
        });

    }
}