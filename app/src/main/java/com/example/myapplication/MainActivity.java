package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;

    View baseline_add;

    mydatabasehelper mydb;

    List<Post> posts;

    LinearLayout scroll;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new mydatabasehelper(MainActivity.this);
        scroll = findViewById(R.id.scrollView);
        posts = mydb.getAllPosts();

        Log.d("postNumber", String.valueOf(posts.size()));

        for (int i = 0; i < posts.size(); i++) {
            CardView cardContainer = new CardView(scroll.getContext());
            cardContainer.setTag(String.valueOf(posts.get(i).getId()));
            cardContainer.setContentPadding(16,16,16,16);
            cardContainer.setCardElevation(4.0f);
            cardContainer.setRadius(12.0f);
            cardContainer.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 16, 0, 16);
            cardContainer.setLayoutParams(layoutParams);


            LinearLayout container = new LinearLayout(cardContainer.getContext());
            container.setOrientation(LinearLayout.VERTICAL);
            container.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));

            TextView title = new TextView(container.getContext()),desc = new TextView(container.getContext());
            title.setText(posts.get(i).getTitre());
            title.setTextColor(Color.BLACK);
            title.setTextAppearance(R.style.h3);

            desc.setTextAppearance(R.style.txt1);

            desc.setText(posts.get(i).getContenu());


            container.addView(title);
            container.addView(desc);
            cardContainer.addView(container);
            scroll.addView(cardContainer);

            cardContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,afficher.class);
                    intent.putExtra("id",cardContainer.getTag().toString());
                    startActivity(intent);
                }
            });
        }

        linearLayout = findViewById(R.id.linearlayout);
        baseline_add = findViewById(R.id.baseline_add);
        baseline_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, baseline_add.class);
                startActivity(intent);


            }
        });




    }
}