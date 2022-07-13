package com.appmovil.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.appmovil.proyecto.AdaptadorPersonalizado;
import com.appmovil.proyecto.entidad.Curso;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PRUEBALISTADO extends AppCompatActivity {

    RecyclerView rvPersonas;

    FirebaseDatabase database;
    DatabaseReference reference;

    private List<Curso> listaPersonas = new ArrayList<>();
    AdaptadorPersonalizado adaptadorPersonalizado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebalistado);
        asignarReferencias();
        inicializarFirebase();
        cargarDatos();
    }




    private void asignarReferencias() {
        rvPersonas = findViewById(R.id.rvMisCursos);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int pos = viewHolder.getAdapterPosition();
                String id = listaPersonas.get(pos).getId();
                listaPersonas.remove(pos);
                adaptadorPersonalizado.notifyDataSetChanged();
                reference.child("Persona").child(id).removeValue();

            }
        }).attachToRecyclerView(rvPersonas);

    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }
    private void cargarDatos() {
        reference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            //Modificación Detección!
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaPersonas.clear();
                for (DataSnapshot item: snapshot.getChildren()){
                    Curso p = item.getValue(Curso.class);
                    listaPersonas.add(p);
                }
                adaptadorPersonalizado = new AdaptadorPersonalizado(PRUEBALISTADO.this,listaPersonas);
                rvPersonas.setAdapter(adaptadorPersonalizado);
                rvPersonas.setLayoutManager(new LinearLayoutManager(PRUEBALISTADO.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}