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

    //Step 1: Declaration
    EditText etLogin;
    EditText etPassword;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Step 2: recuperation des ids
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
            Intent redirectToQuizz = new Intent(Login.this, Quizz1.class);
            startActivity(redirectToQuizz);
        }
    }

    private void signIn(String email, String password) {
            // [START sign_in_with_email]
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Login", "Login:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent redirectToQuizz = new Intent(Login.this, Quizz1.class);
                                startActivity(redirectToQuizz);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Login", "Login:failure", task.getException());
                                Toast.makeText(Login.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                Intent redirectToQuizz = new Intent(Login.this, Quizz1.class);
                                startActivity(redirectToQuizz);
                            }
                        }
                    });
            // [END sign_in_with_email]
        }

    public void showRegister(View view) {
        Intent showRegister = new Intent(Login.this, Register.class);
        startActivity(showRegister);
    }

    public void login(View view){
        //R is a class contain subclasses(id, view..), each subclass contain attributes, each attribute reference to view, ressource..
         etLogin = (EditText)findViewById(R.id.etLogin);
         etPassword = (EditText)findViewById(R.id.etPassword);

        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();

        /*if(login.equals("mohamed") && password.equals("123")){
            Intent showQuizz = new Intent(Login.this, Quizz1.class);
            startActivity(showQuizz);
        }else{
            Toast.makeText(getApplicationContext(), "Username or password is incorrect!!", Toast.LENGTH_SHORT).show();
        }*/

        signIn(login, password);


    }
}
