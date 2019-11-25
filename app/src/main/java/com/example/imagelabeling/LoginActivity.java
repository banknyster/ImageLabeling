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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText emailID,passwordID;
    Button signIn;
    TextView tvSignUp;
    private boolean isLogin = false;

    private FirebaseAuth.AuthStateListener authStateListener;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mFirebaseAuth = FirebaseAuth.getInstance();

        emailID = findViewById(R.id.email);
        passwordID = findViewById(R.id.password);
        signIn = findViewById(R.id.singInBtn);

        tvSignUp = findViewById(R.id.singupTv);

        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

                if(mFirebaseUser != null){
                    isLogin = true;
                    Toast.makeText(LoginActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this,SecondActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Please login",Toast.LENGTH_SHORT).show();
                }
            }
        };

        signIn.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(LoginActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && password.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login failed, please try again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                isLogin = true;
                                Intent i = new Intent(LoginActivity.this,SecondActivity.class);
                                startActivity(i);
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(LoginActivity.this, "Error occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });


    }

    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(authStateListener);
    }
    public void setIsLogin(boolean logedin){
        this.isLogin = logedin;
    }
    public boolean getIsLogin(){
        return this.isLogin;
    }
}
