package com.appmovil.proyecto;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.appmovil.proyecto.entidad.Curso;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.MyViewHolder> {

    private Context context;
    private List<Curso> listaCursos = new ArrayList<>();


    public AdaptadorPersonalizado(Context context, List<Curso> listaCursos){
        this.context = context;
        this.listaCursos = listaCursos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View vista = inflater.inflate(R.layout.fila, parent,false);

        return new MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.filaDescripcion.setText(listaCursos.get(position).getDescripcion()+"");
        holder.filaNombre.setText(listaCursos.get(position).getNombre()+"");
        holder.filaGrado.setText(listaCursos.get(position).getGrado()+"");
        holder.filaCategoria.setText(listaCursos.get(position).getCategoria()+"");
        holder.fila.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context,FormularioCurso.class);
                intent.putExtra("pid", listaCursos.get(position).getId()+"");
                intent.putExtra("pdescripcion", listaCursos.get(position).getDescripcion()+"");
                intent.putExtra("pnombre", listaCursos.get(position).getNombre()+"");
                intent.putExtra("pgrado", listaCursos.get(position).getGrado()+"");
                intent.putExtra("pcategoria", listaCursos.get(position).getCategoria()+"");
                context.startActivity(intent);
                return false;
            }
        });
        holder.filaEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FormularioCurso.class);
                intent.putExtra("pid", listaCursos.get(position).getId()+"");
                intent.putExtra("pdescripcion", listaCursos.get(position).getDescripcion()+"");
                intent.putExtra("pnombre", listaCursos.get(position).getNombre()+"");
                intent.putExtra("pgrado", listaCursos.get(position).getGrado()+"");
                intent.putExtra("pcategoria", listaCursos.get(position).getCategoria()+"");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView filaDescripcion,filaNombre,filaGrado,filaCategoria;
        ImageButton filaEditar, filaEliminar;
        LinearLayout fila;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            filaDescripcion = itemView.findViewById(R.id.filaDescripcion);
            filaNombre = itemView.findViewById(R.id.filaNombre);
            filaGrado = itemView.findViewById(R.id.filaGrado);
            filaCategoria = itemView.findViewById(R.id.filaCategoria);
            filaEditar = itemView.findViewById(R.id.filaEditar);
            filaEliminar = itemView.findViewById(R.id.filaEliminar);
            fila = itemView.findViewById(R.id.fila);

        }
    }
}
