package com.example.quizz_app_o22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class Login extends AppCompatActivity {

    //STEP 1: Declaration
    EditText etLogin;
    EditText etPassword;
    ImageView imageLogo;
    StorageReference storageReference;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //STEP 2: Recuperation des ids
         etLogin = (EditText)findViewById(R.id.etLogin);
         etPassword = (EditText)findViewById(R.id.etPassword);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();



        imageLogo = (ImageView)findViewById(R.id.imgLogo);


        // Reference to an image file in Cloud Storage
         storageReference = FirebaseStorage.getInstance().getReference().child("pictures/logo.png");


        try{

            File localFile = File.createTempFile("images", "png");

            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Local temp file has been created
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    imageLogo.setImageBitmap(bitmap);

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

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(Login.this, Quizz1.class));
        }
    }

    private void signIn(String email, String password) {
            mAuth.signInWithEmailAndPassword(email, password)
			.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity( new Intent(Login.this, Quizz1.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Login.this, "Incorrect Password Or Email.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    public void showRegister(View view) {
        startActivity(new Intent(Login.this, Register.class));
    }

    public void login(View view){
        //R is a class contain subclasses(id, view..), each subclass contain attributes, each attribute reference to view, ressource..
         etLogin = (EditText)findViewById(R.id.etLogin);
         etPassword = (EditText)findViewById(R.id.etPassword);

        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();

        signIn(login, password);


    }
}
