package com.example.quizz_app_o22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Register extends AppCompatActivity {
    EditText etUserName;
    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    private void signUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Sign up successful, you can sign in now & play :)",
                                    Toast.LENGTH_SHORT).show();
                            etUserName.getText().clear();
                            etEmail.getText().clear();
                            etPassword.getText().clear();
                            etConfirmPassword.getText().clear();

                        } else {
                            Toast.makeText(Register.this, "Sign up is fail.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void register(View view){
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etConfirmPassword = (EditText)findViewById(R.id.etConfirmPassword);




        //if(password.equals(confirmPassword)) {
            signUp(etEmail.getText().toString(), etPassword.getText().toString());
        //}
        //else{

            //Toast.makeText(Register.this, "Password field & Confirm Password field are not the same.",
                //    Toast.LENGTH_SHORT).show();
       // }
    }




}