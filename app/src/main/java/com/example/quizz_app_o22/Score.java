package com.example.quizz_app_o22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    private static int score=0;

    public static void incrementScore() {
        score+=1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
       TextView tvScore = (TextView)findViewById(R.id.tvScore);
       String scoreText = score + "/2";
       tvScore.setText(scoreText);
    }

    public void retry(View view){
        score=0;
        Intent retry = new Intent(Score.this, Quizz1.class);
        startActivity(retry);
    }

    public void exit(View view) {
        this.finishAffinity();
    }
}
