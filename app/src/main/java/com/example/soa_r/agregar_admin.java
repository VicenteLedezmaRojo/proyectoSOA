package com.example.soa_r;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class agregar_admin extends AppCompatActivity {
    Button btn_add, Regresar;
    EditText Talla, Genero, Estado, Tipo;
    private FirebaseFirestore mfirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_admin);

        String id = getIntent().getStringExtra("id_articulo");
        mfirestore = FirebaseFirestore.getInstance();

        Talla = findViewById(R.id.TallaA);
        Genero = findViewById(R.id.GeneroA);
        Estado = findViewById(R.id.EstadoA);
        Tipo = findViewById(R.id.TipoA);
        btn_add = findViewById(R.id.btn_add);
        Regresar = findViewById(R.id.RegresarMain);

        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(agregar_admin.this, mein.class));
            }
        });

        if (id == null || id == ""){
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tallaA = Talla.getText().toString().trim();
                    String generoA = Genero.getText().toString().trim();
                    String estadoA = Estado.getText().toString().trim();
                    String tipoA = Tipo.getText().toString().trim();

                    if (tallaA.isEmpty() && generoA.isEmpty() && estadoA.isEmpty() && tipoA.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "ingresa los datos", Toast.LENGTH_SHORT).show();

                    } else {
                        postArticulos(tallaA, generoA, estadoA, tipoA);
                    }
                }
            });
        }else {
            btn_add.setText("Actualizar");
            getArticulo(id);
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tallaA = Talla.getText().toString().trim();
                    String generoA = Genero.getText().toString().trim();
                    String estadoA = Estado.getText().toString().trim();
                    String tipoA = Tipo.getText().toString().trim();

                    if (tallaA.isEmpty() && generoA.isEmpty() && estadoA.isEmpty() && tipoA.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "ingresa los datos", Toast.LENGTH_SHORT).show();

                    } else {
                        updateArticulo(tallaA, generoA, estadoA, tipoA, id);
                    }
                }
            });

        }

    }

    private void getArticulo(String id) {
        mfirestore.collection("ropas").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String tallaA = documentSnapshot.getString("Talla");
                String generoA = documentSnapshot.getString("Genero");
                String estadoA = documentSnapshot.getString("Estado");
                String tipoA = documentSnapshot.getString("Tipo");

                Talla.setText(tallaA);
                Genero.setText(generoA);
                Estado.setText(estadoA);
                Tipo.setText(tallaA);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postArticulos(String tallaA, String generoA, String estadoA, String tipoA) {
        DocumentReference id = mfirestore.collection("ropas").document();

        Map<String, Object> Articulos = new HashMap<>();
        Articulos.put("id", id.getId());
        Articulos.put("Talla", tallaA);
        Articulos.put("Genero", generoA);
        Articulos.put("Estado", estadoA);
        Articulos.put("Tipo", tipoA);


        mfirestore.collection("ropas").document(id.getId()).set(Articulos).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(agregar_admin.this, mein.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateArticulo(String tallaA, String generoA, String estadoA, String tipoA, String id) {
        Map<String, Object> Articulos = new HashMap<>();
        Articulos.put("Talla", tallaA);
        Articulos.put("Genero", generoA);
        Articulos.put("Estado", estadoA);
        Articulos.put("Tipo", tipoA);

        mfirestore.collection("ropas").document(id).update(Articulos).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Actualizado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR AL ACTUALIZAR", Toast.LENGTH_SHORT).show();
            }
        });
    }

}