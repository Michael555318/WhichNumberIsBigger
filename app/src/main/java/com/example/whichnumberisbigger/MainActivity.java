package com.example.whichnumberisbigger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonLeft;
    private Button buttonRight;
    private TextView textViewScore;
    private int score;
    private int leftNumber;
    private int rightNumber;
    public static final int MAX_VALUE = 1000;
    public static final int MIN_VALUE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        randomizeAndUpdateDisplay();
    }

    private void randomizeAndUpdateDisplay() {
        //TODO: set the score, randomize numbers, set the button values
        String scoreDisplay = (String) (getResources().getText(R.string.textview_main_score))+score;
        textViewScore.setText(scoreDisplay);
        randomizeNumbers();
        buttonLeft.setText(leftNumber);
        buttonRight.setText(rightNumber);
    }

    private void randomizeNumbers() {
        do {
        leftNumber = generateNumber();
        rightNumber = generateNumber();
        } while (leftNumber != rightNumber);
    }

    private int generateNumber() {
        int range = MAX_VALUE - MIN_VALUE + 1;
        return MIN_VALUE + (int)(Math.random()*range);
    }

    private void wireWidgets() {
        buttonLeft = findViewById(R.id.button_main_left);
        buttonRight = findViewById(R.id.button_main_right);
        textViewScore = findViewById(R.id.textview_main_score);
    }

    public void onLeftClick(View view) {
        checkAnswer(true);
    }

    public void onRightClick(View view) {
        checkAnswer(false);
    }

    public void checkAnswer(Boolean leftPressed) {
        if (leftNumber > rightNumber && leftPressed) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else if (rightNumber > leftNumber && !leftPressed) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "False!", Toast.LENGTH_SHORT).show();
            if (score >= 0) {
                score--;
            }
        }
        randomizeAndUpdateDisplay();
    }
}
