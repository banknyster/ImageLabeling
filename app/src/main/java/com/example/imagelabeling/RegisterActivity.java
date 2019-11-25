package com.example.imagelabeling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText emailID,passwordID;
    Button signUp;
    TextView tvSignIn;

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mFirebaseAuth = FirebaseAuth.getInstance();

        emailID = findViewById(R.id.email);
        passwordID = findViewById(R.id.password);
        signUp = findViewById(R.id.singUpBtn);

        tvSignIn = findViewById(R.id.loginTv);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailID.getText().toString();
                String password = passwordID.getText().toString();

                if(email.isEmpty()){
                    emailID.setError("Plaase enter email");
                    emailID.requestFocus();
                }
                else if(password.isEmpty()){
                    passwordID.setError("Plaase enter password");
                    passwordID.requestFocus();
                }
                else if(email.isEmpty() && password.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && password.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Sign up unsuccessful, please try again", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                LoginActivity login = new LoginActivity();
                                login.setIsLogin(true);
                                startActivity(new Intent(RegisterActivity.this,SecondActivity.class));
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(RegisterActivity.this, "Error occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }
}
