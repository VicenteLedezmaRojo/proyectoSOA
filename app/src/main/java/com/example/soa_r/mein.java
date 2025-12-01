package com.example.soa_r;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.soa_r.adapter.ropasAdapter;
import com.example.soa_r.model.ropas;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class mein extends AppCompatActivity {
    Button btn_close, btn_add;
    RecyclerView mRecycler;
    ropasAdapter mAdapter;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mein);
        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recyclerViewSingle2);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("ropas");

        FirestoreRecyclerOptions<ropas> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<ropas>().setQuery(query, ropas.class).build();

        mAdapter = new ropasAdapter(firestoreRecyclerOptions,this);
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

    public void Registrar(View view){
        Intent ir = new Intent(this, agregar_admin.class);
        startActivity(ir);
    }
    public void Salir(View view){
        Intent ir = new Intent(this, MainAdmin.class);
        startActivity(ir);
    }
}