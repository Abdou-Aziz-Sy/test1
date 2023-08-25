package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class baseline_add extends AppCompatActivity {
    EditText titre, contenu;
    Button add_button;

    View close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_second);

        titre = findViewById(R.id.titre);
        contenu = findViewById(R.id.contenu);
        add_button = findViewById(R.id.add_button);
        close = findViewById(R.id.button);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(baseline_add.this,MainActivity.class);
                startActivity(intent);
            }
        });


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydatabasehelper mydb = new mydatabasehelper(baseline_add.this);
                mydb.addbook(titre.getText().toString().trim(),
                        contenu.getText().toString().trim());
            }
        });
    }
}