package com.appmovil.proyecto;

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
import com.google.firebase.auth.*;

public class MainActivity extends AppCompatActivity {

    Button btnIngresar;
    EditText txtUsuario,txtPassword;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        asignarReferencias();
    }

    private void asignarReferencias() {
        btnIngresar = findViewById(R.id.btnIngresar);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);

        firebaseAuth = FirebaseAuth.getInstance();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = txtUsuario.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if(usuario.isEmpty() && password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Ingrese los Datos", Toast.LENGTH_SHORT).show();
                }else{
                    login(usuario,password);
                }

            }
        });
    }

    private void login(String usuario, String password) {
        firebaseAuth.signInWithEmailAndPassword(usuario,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(MainActivity.this,PrincipalActivity.class));
                    Toast.makeText(MainActivity.this, "Beinvenido", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}