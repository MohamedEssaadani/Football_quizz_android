package com.example.quizz_app_o22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class Quizz1 extends AppCompatActivity {

    ImageView quizz1Image;
    StorageReference storageReference;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz1);


        quizz1Image = (ImageView)findViewById(R.id.quizz1Image);


        // Reference to an image file in Cloud Storage
        storageReference = FirebaseStorage.getInstance().getReference().child("pictures/cl.jpg");


        try{

            File localFile = File.createTempFile("images", "jpg");

            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Local temp file has been created
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    quizz1Image.setImageBitmap(bitmap);

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


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("questions").child("cl").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());

                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    final RadioButton rbOptionOne = (RadioButton)findViewById(R.id.rbOptionOne);
                    rbOptionOne.setText(task.getResult().child("optionOne").getValue().toString());

                    final RadioButton rbOptionTwo = (RadioButton)findViewById(R.id.rbOptionTwo);
                    rbOptionTwo.setText(task.getResult().child("optionTwo").getValue().toString());

                    final RadioButton rbOptionThree = (RadioButton)findViewById(R.id.rbOptionThree);
                    rbOptionThree.setText(task.getResult().child("optionThree").getValue().toString());
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