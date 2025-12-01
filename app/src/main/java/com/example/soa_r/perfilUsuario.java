package com.example.soa_r;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class perfilUsuario extends AppCompatActivity {
    Button btnU;
    EditText contraseña,nombre;
    TextView nDonacion,fechaU;
    private FirebaseAuth mAuth;
    int contador = 0;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference nStorageRef;
    String id = FirebaseAuth.getInstance().getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


                nDonacion=findViewById(R.id.txtCantidad);
                nombre=findViewById(R.id.txtUsuario);
                contraseña=findViewById(R.id.txtClave);
                btnU = findViewById(R.id.btnUpdate);


                btnU.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = nombre.getText().toString().trim();
                        String pswUp = contraseña.getText().toString().trim();

                        if (name.isEmpty()  && pswUp.isEmpty()){
                            Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_LONG).show();
                        }else{
                            updateUser(name,pswUp,id);
                        }

                    }
                });
            }

            private void updateUser(String name, String pswUp, String id) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", name);
                map.put("password", pswUp);

                firebaseFirestore.collection("user").document(id).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Actualizado correctamente", Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al actualizar", Toast.LENGTH_LONG).show();
                    }
                });
            }

            private void getUser(String id){
                firebaseFirestore.collection("user").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String nameU = documentSnapshot.getString("name");
                        String pswUR = documentSnapshot.getString("password");
                        String cantidadD = documentSnapshot.getString("cantidad");
                        /*Date fecha = documentSnapshot.getDate("email");*/
                        nombre.setText(nameU);
                        contraseña.setText(pswUR);
                        nDonacion.setText(cantidadD);
                        /*SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");
                        String strDt = simpleDate.format(fecha);
                        fechaU.setText(strDt);*/

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al obtener datos", Toast.LENGTH_LONG).show();
                    }
                });

            }


    @Override
    protected void onStart() {
        super.onStart();
        getUser(id);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                startActivity(new Intent(perfilUsuario.this,registroRopa.class));
                return true;
            case R.id.item2:
                startActivity(new Intent(perfilUsuario.this,vistaDatos.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(perfilUsuario.this,VerRopa.class));
                return true;
            case R.id.item4:
                cerrar();

            case R.id.item5:
                startActivity(new Intent(perfilUsuario.this,perfilUsuario.class));
                return true;
            default:

        }
        return super.onOptionsItemSelected(item);
    }
    public void cerrar(){
        mAuth.signOut();
        finish();
        startActivity(new Intent(perfilUsuario.this, MainActivity.class));
        Toast.makeText(perfilUsuario.this, "Haz cerrado sesión satisfactoriamente", Toast.LENGTH_SHORT).show();
    }
}


