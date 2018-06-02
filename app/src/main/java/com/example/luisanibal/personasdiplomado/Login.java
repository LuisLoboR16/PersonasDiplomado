package com.example.luisanibal.personasdiplomado;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private EditText password;
    private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.txtUsuario);
        password = findViewById(R.id.txtContrasena);
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            }
        };
    }

    public void crearUsuario(String email,String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                Toast.makeText(Login.this,getResources().getString(R.string.usuario_creado),Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(Login.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void logear(String email,String password){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(Login.this,Principal.class);
                    startActivity(i);
                }else{
                    Toast.makeText(Login.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
    protected void onStop(){
        super.onStop();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
    public void ingresar(View v){
        logear(email.getText().toString(),password.getText().toString());
    }
    public void crear(View v){
        crearUsuario(email.getText().toString(),password.getText().toString());
    }
}
