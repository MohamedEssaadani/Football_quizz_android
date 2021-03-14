package com.example.quizz_app_o22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }


    public void showRegister(View view) {
        Intent showRegister = new Intent(Login.this, Register.class);
        startActivity(showRegister);
    }

    public void login(View view){
        //R is a class contain subclasses(id, view..), each subclass contain attributes, each attribute reference to view, ressource..
        EditText etLogin = (EditText)findViewById(R.id.etLogin);
        EditText etPassword = (EditText)findViewById(R.id.etPassword);

        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();

        if(login.equals("mohamed") && password.equals("123")){
            Intent showQuizz = new Intent(Login.this, Quizz1.class);
            startActivity(showQuizz);
        }else{
            Toast.makeText(getApplicationContext(), "Username or password is incorrect!!", Toast.LENGTH_SHORT).show();
        }


    }
}
