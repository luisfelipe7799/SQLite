package com.example.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejemplosqlite.R;
import com.example.objetos.Asignaturas;
import com.example.objetos.Usuario;
import com.example.sqlite.OperacionesCRUD;
import com.example.sqlite.esquemas.Asignatura;
import com.example.sqlite.esquemas.User;

import java.util.ArrayList;

public class AdaptadorAsignaturas extends RecyclerView.Adapter<AdaptadorAsignaturas.AsignaturasHolder> {

    private ArrayList<Asignaturas> asignaturasDesplegar;



    public static class AsignaturasHolder extends RecyclerView.ViewHolder{


        public TextView codigo;
        public TextView descripcion;
        public ImageButton delete;


        public AsignaturasHolder(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.codeAsig);
            descripcion = itemView.findViewById(R.id.descAsig);

            delete = itemView.findViewById(R.id.eliminar_asig);

        }


    }

    public AdaptadorAsignaturas(ArrayList<Asignaturas> asignaturasDesplegarIn) {
        asignaturasDesplegar = asignaturasDesplegarIn;
    }

    @NonNull
    @Override
    public AsignaturasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asignatura,parent,
                false);


        AsignaturasHolder holder = new AsignaturasHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AsignaturasHolder holder, int position) {

        Asignaturas item = asignaturasDesplegar.get(position);


        holder.codigo.setText(item.getId_asignatura()+": "
                +item.getCodigo());

        holder.descripcion.setText(item.getDescripcion());

        holder.delete.setId(item.getId_asignatura());
        //setear listener a cada boton eliminar de cada  item de la lista de asignaturas
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String condicion = "id_asignatura=?";
                String valores[] = {""+item.getId_asignatura()};
                int cant_regs_eliminados= 0;

                OperacionesCRUD instancia = new OperacionesCRUD(view.getContext(),"miBD",
                        null,4);
                cant_regs_eliminados = instancia.borrarRegistro(Asignatura.Esquema.TABLE_NAME,condicion,valores);

                if(cant_regs_eliminados>0){
                    Toast.makeText(view.getContext(), "Asignatura eliminada", Toast.LENGTH_SHORT).show();
                    AdaptadorAsignaturas.this.asignaturasDesplegar.remove(holder.getAdapterPosition());
                    AdaptadorAsignaturas.this.notifyDataSetChanged();
                }else{
                    Toast.makeText(view.getContext(), "Error eliminado de asignatura", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return asignaturasDesplegar.size();
    }

}
