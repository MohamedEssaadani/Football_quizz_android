package com.example.quizz_app_o22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Quizz2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz2);
    }

    public boolean check(){
        RadioGroup rg = (RadioGroup) findViewById(R.id.rgQuestions2);
        final String checkedValue =
                ((RadioButton)findViewById(rg.getCheckedRadioButtonId()))
                        .getText().toString();
        return checkedValue.equals("Fabio Cannavaro");
    }
    public void showScore(View view){
        if(check())
            Score.incrementScore();

        Intent showNext = new Intent(Quizz2.this, Score.class);
        startActivity(showNext);
    }
}