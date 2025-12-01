package com.example.soa_r;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainAdmin extends AppCompatActivity {


    EditText correoAdm, contraseñaAdm;
    Button registroAdd;
    FirebaseAuth mAucth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        mAucth = FirebaseAuth.getInstance();

        correoAdm = findViewById(R.id.correoAdm);
        contraseñaAdm = findViewById(R.id.contraseñaAdm);
        registroAdd = findViewById(R.id.registroAdd);

        registroAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAdmin = correoAdm.getText().toString().trim();
                String passAdmin = contraseñaAdm.getText().toString().trim();

                if (emailAdmin.isEmpty() && passAdmin.isEmpty()) {
                    Toast.makeText(MainAdmin.this,"Ingresa los DATOS",Toast.LENGTH_SHORT).show();
                }else {
                    loginAdmin(emailAdmin, passAdmin);
                }
            }
        });
    }
    private void loginAdmin(String emailAdmin, String passAdmin) {
        mAucth.signInWithEmailAndPassword(emailAdmin,passAdmin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(MainAdmin.this,mein.class));
                    Toast.makeText(MainAdmin.this,"Bienvenido",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainAdmin.this,"ERROR",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainAdmin.this,"ERROR al iniciar SESION",Toast.LENGTH_SHORT).show();
            }
        });
    }
}