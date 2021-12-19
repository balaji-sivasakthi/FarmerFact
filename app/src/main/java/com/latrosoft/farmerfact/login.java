package com.latrosoft.farmerfact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    Button login;
    EditText email,pass;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.loginbtn);
        email = findViewById(R.id.emailEv);
        pass = findViewById(R.id.passEv);
        firebaseAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(email.getText().toString(),pass.getText().toString());
//                Intent  i = new Intent(login.this,Dashboard.class);
//                startActivity(i);
            }
        });

    }
    void login(String email,String pass){
        firebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent  i = new Intent(login.this,Dashboard.class);
                    startActivity(i);

                }else{
                    Toast.makeText(getApplicationContext(),"Invaild Credential",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}