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
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;





public class registroRopa extends AppCompatActivity {
    EditText txttalla, txtgenero, txtestado, txttipo;
    Button btnRegistrar, imen;

    private FirebaseFirestore mfirestore;
    private FirebaseAuth mAuth;
    String id = FirebaseAuth.getInstance().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ropa);

        txttalla = findViewById(R.id.Talla);
        txtgenero = findViewById(R.id.Genero);
        txtestado = findViewById(R.id.Estado);
        txttipo = findViewById(R.id.Tipo);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        imen = findViewById(R.id.imen1);

        mfirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String talla1 = txttalla.getText().toString().trim();
                String genero1 = txtgenero.getText().toString().trim();
                String estado1 = txtestado.getText().toString().trim();
                String tipo1 = txttipo.getText().toString().trim();

                if (talla1.isEmpty() && genero1.isEmpty() && estado1.isEmpty() && tipo1.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "ingresa los datos", Toast.LENGTH_SHORT).show();

                } else {
                    postRopa(talla1, genero1, estado1, tipo1);
                   // updateUser(id);
                }
            }
        });

       imen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registroRopa.this, AgregarImg.class));
            }
        });

    }//end onCrate

    private void postRopa(String talla1, String genero1, String estado1, String tipo1) {

        DocumentReference id = mfirestore.collection("ropas").document();

        Map<String, Object> ropas = new HashMap<>();

        ropas.put("id", id.getId());
        ropas.put("Talla", talla1);
        ropas.put("Genero", genero1);
        ropas.put("Estado", estado1);
        ropas.put("Tipo", tipo1);

        mfirestore.collection("ropas").document(id.getId()).set(ropas).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(registroRopa.this, VerRopa.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();
            }
        });

    }
   /* private void updateUser(String id){
        mfirestore.collection("user").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Integer cantidad = Integer.valueOf(documentSnapshot.getString("cantidad"));
                Toast.makeText(registroRopa.this, "Correcto", Toast.LENGTH_SHORT).show();
                cantidad = cantidad + 1;
                String cantidadS = Integer.toString(cantidad);
                Map<String, Object> map = new HashMap<>();
                map.put("cantidad",cantidadS);
                mfirestore.collection("user").document(id).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al obtener datos", Toast.LENGTH_LONG).show();
            }
        });

    }*/

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
                startActivity(new Intent(registroRopa.this,registroRopa.class));
                return true;
            case R.id.item2:
                startActivity(new Intent(registroRopa.this,vistaDatos.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(registroRopa.this,VerRopa.class));
                return true;
            case R.id.item4:
                cerrar();
            case R.id.item5:
                startActivity(new Intent(registroRopa.this,VerRopa.class));
                return true;
            default:

        }
        return super.onOptionsItemSelected(item);


    }

    public void cerrar(){
        mAuth.signOut();
        finish();
        startActivity(new Intent(registroRopa.this, MainActivity.class));
        Toast.makeText(registroRopa.this, "Haz cerrado sesión satisfactoriamente", Toast.LENGTH_SHORT).show();
    }
    /*public void Agregar(View view){
        Intent siguiente1 = new Intent(this, AgregarImg.class);
        startActivity(siguiente1);
    }*/

}
