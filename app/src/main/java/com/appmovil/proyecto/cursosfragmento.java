package com.appmovil.proyecto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.appmovil.proyecto.entidad.Curso;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cursosfragmento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cursosfragmento extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context context;

    public cursosfragmento(Context context) {
        // Required empty public constructor
        this.context = context;
    }
    public cursosfragmento() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cursosfragmento.
     */
    // TODO: Rename and change types and number of parameters
    public static cursosfragmento newInstance(String param1, String param2) {
        cursosfragmento fragment = new cursosfragmento();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    ImageButton btnAgregar;

    public void asignarReferencias(View v){
        btnAgregar = v.findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(),FormularioCurso.class));
                Toast.makeText(getContext(), "HOLA :X", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_cursosfragmento, container, false);
        asignarReferencias(root);
        asignarReferencias2(root);
        inicializarFirebase();
        cargarDatos();
        return  root;
    }
    //Listado
    RecyclerView rvCursos;

    FirebaseDatabase database;
    DatabaseReference reference;

    private List<Curso> listaCursos = new ArrayList<>();
    AdaptadorPersonalizado adaptadorPersonalizado;

    private void asignarReferencias2(View v) {
        rvCursos = v.findViewById(R.id.rvCursos);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int pos = viewHolder.getAdapterPosition();
                String id = listaCursos.get(pos).getId();
                listaCursos.remove(pos);
                adaptadorPersonalizado.notifyDataSetChanged();
                reference.child("Persona").child(id).removeValue();

            }
        }).attachToRecyclerView(rvCursos);

    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(getContext());
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }
    private void cargarDatos() {
        reference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            //Modificación Detección!
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaCursos.clear();
                for (DataSnapshot item: snapshot.getChildren()){
                    Curso c = item.getValue(Curso.class);
                    listaCursos.add(c);
                }
                adaptadorPersonalizado = new AdaptadorPersonalizado(getContext(), listaCursos);
                rvCursos.setAdapter(adaptadorPersonalizado);
                rvCursos.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}