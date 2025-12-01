package com.example.soa_r;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.soa_r.adapter.ropaAdapterUser;
import com.example.soa_r.adapter.ropasAdapter;
import com.example.soa_r.model.ropas;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class VerRopa extends AppCompatActivity {
    RecyclerView mRecycler;
    ropaAdapterUser mAdapter;
    FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_ropa);
        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recyclerViewSingle2);
        mAuth = FirebaseAuth.getInstance();
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("ropas");

        FirestoreRecyclerOptions<ropas> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<ropas>().setQuery(query, ropas.class).build();

        mAdapter = new ropaAdapterUser(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.startListening();
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
                startActivity(new Intent(VerRopa.this,registroRopa.class));
                return true;
            case R.id.item2:
                startActivity(new Intent(VerRopa.this,vistaDatos.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(VerRopa.this,VerRopa.class));
                return true;
            case R.id.item4:
                cerrar();

            case R.id.item5:
                startActivity(new Intent(VerRopa.this,perfilUsuario.class));
                return true;
            default:

        }
        return super.onOptionsItemSelected(item);
    }
    public void cerrar(){
        mAuth.signOut();
        finish();
        startActivity(new Intent(VerRopa.this, MainActivity.class));
        Toast.makeText(VerRopa.this, "Haz cerrado sesión satisfactoriamente", Toast.LENGTH_SHORT).show();
    }
}