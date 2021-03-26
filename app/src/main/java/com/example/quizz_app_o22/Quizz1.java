package com.example.quizz_app_o22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Quizz1 extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz1);

        imageView = (ImageView)findViewById(R.id.imgQuestion);
        getData();
    }

    //Get questions from firebase
    public void getData(){
        FirebaseFirestore
                .getInstance()
                .collection("cities")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                int resImage = getResources().getIdentifier(document.getString("image") , "drawable", getPackageName());
                                imageView.setImageResource(resImage);

                            }
                        } else {
                            Toast.makeText(Quizz1.this, "ERROR!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public boolean check(){
        RadioGroup rg = (RadioGroup) findViewById(R.id.rgQuestions);
        final String checkedValue =
                ((RadioButton)findViewById(rg.getCheckedRadioButtonId()))
                        .getText().toString();
        return checkedValue.equals("Liverpool");
    }
    public void next(View view){
        if(check())
            Score.incrementScore();

        Intent showNext = new Intent(Quizz1.this, Quizz2.class);
        startActivity(showNext);
    }
}