package com.example.testpokeapi;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button pokedexButton, quizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizButton = findViewById(R.id.buttonQuiz);
        pokedexButton = findViewById(R.id.buttonPokedex);

        //show fragment when pokedex button is clicked
        pokedexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                DetailFragment fragment = new DetailFragment();
                fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
            }
        });

        // go to quiz activity when quiz button is clicked
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(i);
            }
        });
    }
}
