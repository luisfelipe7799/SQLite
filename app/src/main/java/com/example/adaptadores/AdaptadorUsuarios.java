package com.example.adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejemplosqlite.EditUsuario;
import com.example.ejemplosqlite.R;
import com.example.objetos.Usuario;
import com.example.sqlite.OperacionesCRUD;
import com.example.sqlite.esquemas.User;

import java.util.ArrayList;

public class AdaptadorUsuarios  extends RecyclerView.Adapter<AdaptadorUsuarios.UsuarioHolder> {

    private ArrayList<Usuario> usuarioDesplegar;



    public static class UsuarioHolder extends RecyclerView.ViewHolder{

        public ImageView avatar;
        public TextView nombre;
        public TextView rut;
        public ImageButton eliminar;
        public ImageButton detalle;
        public ImageButton editar;

        public UsuarioHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            nombre = itemView.findViewById(R.id.name);
            rut = itemView.findViewById(R.id.rut);
            eliminar = itemView.findViewById(R.id.eliminar);
            detalle = itemView.findViewById(R.id.detalle);
            editar = itemView.findViewById(R.id.editar);
        }


    }

    public AdaptadorUsuarios(ArrayList<Usuario> usuarioDesplegarIn) {
        usuarioDesplegar = usuarioDesplegarIn;
    }

    @NonNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario,parent,
                false);

        UsuarioHolder holder = new UsuarioHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder holder, int position) {
        Usuario item = usuarioDesplegar.get(position);
        if(item.getGenero().toUpperCase().equals("MASCULINO"))
            holder.avatar.setImageResource(R.drawable.avatar_mas);
        else
            holder.avatar.setImageResource(R.drawable.avatar_fem);

        holder.nombre.setText(item.getId_usuario()+": "
        +item.getNombre()+""
        +item.getApePaterno()+""
        +item.getApeMaterno());

        holder.rut.setText(item.getEmail());
        holder.detalle.setId(item.getId_usuario());
        //boton eliminar
        holder.eliminar.setId(item.getId_usuario());
        //setear listener a cada boton eliminar de cada  item de la lista de usuarios
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String condicion = "id_usuario=?";
                String valores[] = {""+item.getId_usuario()};
                int cant_regs_eliminados= 0;

                OperacionesCRUD instancia = new OperacionesCRUD(view.getContext(),"miBD",
                        null,4);
                cant_regs_eliminados = instancia.borrarRegistro(User.Esquema.TABLE_NAME,condicion,valores);

                if(cant_regs_eliminados>0){
                    Toast.makeText(view.getContext(), "Usuario eliminado", Toast.LENGTH_SHORT).show();
                    AdaptadorUsuarios.this.usuarioDesplegar.remove(holder.getAdapterPosition());
                    AdaptadorUsuarios.this.notifyDataSetChanged();
                }else{
                    Toast.makeText(view.getContext(), "Error eliminado de usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });



        //boton editar
        holder.editar.setId(item.getId_usuario());
        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editarUsuario = new Intent(view.getContext(), EditUsuario.class);

                editarUsuario.putExtra("id",item.getId_usuario());
                editarUsuario.putExtra("nom",item.getNombre().toString());
                editarUsuario.putExtra("apepaterno",item.getApePaterno().toString());
                editarUsuario.putExtra("apematerno",item.getApeMaterno().toString());
                editarUsuario.putExtra("email",item.getEmail().toString());
                editarUsuario.putExtra("edad",item.getEdad());
                editarUsuario.putExtra("telefono",item.getTelefono());
                editarUsuario.putExtra("direccion",item.getDireccion().toString());
                editarUsuario.putExtra("genero",item.getGenero().toString());

                view.getContext().startActivity(editarUsuario);

            }
        });
    }

    @Override
    public int getItemCount() {
        return usuarioDesplegar.size();
    }

}
