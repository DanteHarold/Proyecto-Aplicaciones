package com.appmovil.proyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.appmovil.proyecto.entidad.Curso;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;

public class FormularioCurso extends AppCompatActivity {

    EditText txtDescripcion, txtNombre, txtGrado, txtCategoria;
    Button btnRegistrar;
    TextView lblTitulo;

    Curso curso;

    FirebaseDatabase database;
    DatabaseReference reference;

    boolean registra = true;
    String id;
    HashMap map = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_curso);
        asignarReferencias();
        inicializarFirebase();
        obtenerValores();
    }

    @Override
    public void onPause(){
        super.onPause();

        overridePendingTransition(ListadoActivity.zoomIn,ListadoActivity.zoomOut);
    }


    private void obtenerValores() {
        if(getIntent().hasExtra("pid")){

            lblTitulo.setText("Modificando Curso");
            btnRegistrar.setText("Modificar Curso");
            registra = false;
            id = getIntent().getStringExtra("pid");
            txtDescripcion.setText(getIntent().getStringExtra("pdescripcion"));
            txtNombre.setText(getIntent().getStringExtra("pnombre"));
            txtGrado.setText(getIntent().getStringExtra("pgrado"));
            txtCategoria.setText(getIntent().getStringExtra("pcategoria"));
        }
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

    }

    private void asignarReferencias() {
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtNombre = findViewById(R.id.txtNombre);
        txtGrado = findViewById(R.id.txtGrado);
        txtCategoria = findViewById(R.id.txtCategoria);
        lblTitulo = findViewById(R.id.lblTitulo);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(v -> {

            if(capturarDatos()){
                String msaje = "";
                if(registra){
                    reference.child("Persona").child(curso.getId()).setValue(curso);
                    ventana("Curso Agregado","").show();
                }else{
                    reference.child("Persona").child(id).updateChildren(map);
                    ventana("Curso Actualizado","").show();
                }
            }else{
                ventana("Completa todos los campos.","").show();
            }
        });
    }
    private boolean capturarDatos(){
        boolean valida  = true;
        String descripcion = txtDescripcion.getText().toString();
        String nombre = txtNombre.getText().toString();
        String grado = txtGrado.getText().toString();
        String categoria = txtCategoria.getText().toString();
        if(descripcion.equals("")){
            txtDescripcion.setError("La Descripción es Obligatorio");
            valida = false;
        }
        if(nombre.equals("")){
            txtNombre.setError("El Nombre es Obligatorio");
            valida = false;
        }
        if(grado.equals("")){
            txtGrado.setError("El Grado es Obligatoria");
            valida = false;
        }
        if(categoria.equals("")){
            txtCategoria.setError("La Categoria es Obligatorio");
            valida = false;
        }
        if(valida){
            if(registra){
                curso = new Curso(UUID.randomUUID().toString(),descripcion,nombre,grado,categoria);
            }else{
                map.put("descripcion",descripcion);
                map.put("nombre",nombre);
                map.put("grado",grado);
                map.put("categoria",categoria);
            }
        }
        return valida;
    }
    private AlertDialog ventana(String msaje, String msje2){
        AlertDialog.Builder builder = new AlertDialog.Builder(FormularioCurso.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();


        TextView text = view.findViewById(R.id.txtResultados);

        text.setText(msaje+msje2);

        Button btnAceptar = view.findViewById(R.id.btnAceptars);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FormularioCurso.this,PrincipalActivity.class);
                startActivity(intent);

            }
        });

        return dialog;
    }
}