package com.example.soa_r;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth autenticacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autenticacion=FirebaseAuth.getInstance();

        Button registro, acceso;
        EditText email, pass;
        ImageButton btnAdminn;


        email = findViewById(R.id.correo);
        pass = findViewById(R.id.contraseña);

        registro = findViewById(R.id.registroAdd);
        acceso = findViewById(R.id.reg);
        btnAdminn = findViewById(R.id.btnAdmin);


        btnAdminn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainAdmin.class));
            }
        });
        acceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, pantallaP.class));
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cor=email.getText().toString().trim();
                String passw=pass.getText().toString().trim();
                if(cor.isEmpty() && passw.isEmpty()){
                    Toast.makeText(MainActivity.this,"Ingresa los DATOS",Toast.LENGTH_SHORT).show();
                }else{
                    longinUser(cor,passw);
                }
            }
        });
    }


    private void longinUser(String cor, String passw){
        autenticacion.signInWithEmailAndPassword(cor,passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(MainActivity.this, registroRopa.class));
                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "ERROR Al iniciar la Sesion", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*protected void onStart(){
        super.onStart();
        FirebaseUser usuario = autenticacion.getCurrentUser();
        if (usuario != null){
            startActivity(new Intent(MainActivity.this, perfilUsuario.class));
            finish();
        }
    }*/


}