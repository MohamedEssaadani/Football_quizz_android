package com.example.quizz_app_o22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class Quizz2 extends AppCompatActivity {
    ImageView quizz2Image;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz2);

        quizz2Image = (ImageView)findViewById(R.id.quizz2Image);


        // Reference to an image file in Cloud Storage
        storageReference = FirebaseStorage.getInstance().getReference().child("pictures/bd.jpg");


        try{

            File localFile = File.createTempFile("images", "jpg");

            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Local temp file has been created
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    quizz2Image.setImageBitmap(bitmap);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });
        }catch (Exception ex){
            ex.getStackTrace();
        }

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