package com.example.quizz_app_o22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    //STEP 1: Declaration
    EditText etLogin;
    EditText etPassword;

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
