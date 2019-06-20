package com.example.testpokeapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button pokedexButton;
    private ImageButton hintButton;
    private int currentImageIndex = 0;
    private int[] imagineNumbers;
    private ImageView pokemonImage;
    private Button checkButton;
    private EditText userInput;
    private String[] pokemonName;
    private String userAnswer;
    private int nameCounter;
    private Animation anim;
    private View mGuessImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        pokemonName = new String[] {"Magikarp", "Caterpie", "Ditto", "Horsea", "Onix", "Pidgeot", "Seel", "Tentacruel"};
        nameCounter = 0;
        pokemonImage = findViewById(R.id.pokemonGuess);
        checkButton = findViewById(R.id.submitAnswerButton);
        imagineNumbers = new int[]{R.drawable.magikarp, R.drawable.caterpie, R.drawable.ditto, R.drawable.horsea,
                R.drawable.onix, R.drawable.pidgeot, R.drawable.seel, R.drawable.tentacruel};
        userInput = findViewById(R.id.answerInput);
        pokedexButton = findViewById(R.id.pokedexButton);
        hintButton = findViewById(R.id.hintButton);
        mGuessImage = findViewById(R.id.pokemonGuess);
        anim = AnimationUtils.loadAnimation(this, R.anim.scale);


        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer();
            }
        });

        pokedexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuizActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuizActivity.this, HintActivity.class);
                startActivity(i);
            }
        });
    }

    // call the method when the checkButton is clicked
    private void submitAnswer() {
        userAnswer = userInput.getText().toString();
        // compare string userinput and the string name inside pokemonName array --- in string
        if (userAnswer.equalsIgnoreCase(pokemonName[nameCounter])) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            currentImageIndex++;
            nameCounter++;
            mGuessImage.startAnimation(anim);
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }


        if (currentImageIndex >= imagineNumbers.length) {
            currentImageIndex = 0;
            nameCounter = 0;
        }
        pokemonImage.setImageResource(imagineNumbers[currentImageIndex]);
    }
}
