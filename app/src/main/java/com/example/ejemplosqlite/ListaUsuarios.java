package com.example.ejemplosqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.adaptadores.AdaptadorUsuarios;
import com.example.objetos.Usuario;
import com.example.sqlite.OperacionesCRUD;
import com.example.sqlite.esquemas.User;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuarios extends AppCompatActivity {

    private RecyclerView milista;
    private RecyclerView.LayoutManager manejador;
    private RecyclerView.Adapter adaptador;

    Button irListaAsig;
    Button irAgregarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        irListaAsig = (Button) findViewById(R.id.irListaAsignatura);
        irAgregarUsuario = (Button) findViewById(R.id.agregarUsuario);

        irListaAsig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaUsuarios.this, ListaAsignaturas.class);
                startActivity(intent);
            }
        });
        irAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaUsuarios.this, MainActivity.class);
                startActivity(intent);
            }
        });

        OperacionesCRUD instancia = new OperacionesCRUD(this,"miBD",null,4);

        String[] columnasUsuario = User.Esquema.ALLCOLUMNAS;
        String condicion = "";
        String[] valCondicion = {};

        List<ContentValues> usuariosObtenidos = instancia.obtenerDatos(columnasUsuario, condicion,
                valCondicion,"usuario");


        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        if(usuariosObtenidos == null){
            Toast.makeText(this, "No se  obtuvieron usuarios desde base de datos", Toast.LENGTH_SHORT).show();

        }else{
            for(int i=0; i < usuariosObtenidos.size(); i++){
                ContentValues auxiliar = usuariosObtenidos.get(i);
                System.out.println("Entra a for");
                Usuario nuevoItemUsuario = new Usuario();
                for(String key: auxiliar.keySet()){
                    switch (key.toString()){
                        case User.Esquema.ID:
                            nuevoItemUsuario.setId_usuario(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                        case User.Esquema.NOMBRE:
                            nuevoItemUsuario.setNombre(auxiliar.get(key).toString());
                            break;
                        case User.Esquema.APEPATERNO:
                            nuevoItemUsuario.setApePaterno(auxiliar.get(key).toString());
                            break;
                        case User.Esquema.APEMATERNO:
                            nuevoItemUsuario.setApeMaterno(auxiliar.get(key).toString());
                            break;
                        case User.Esquema.EMAIL:
                            nuevoItemUsuario.setEmail(auxiliar.get(key).toString());
                            break;
                        case User.Esquema.EDAD:
                            nuevoItemUsuario.setEdad(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                        case User.Esquema.DIRECCION:
                            nuevoItemUsuario.setDireccion(auxiliar.get(key).toString());
                            break;
                        case User.Esquema.GENERO:
                            nuevoItemUsuario.setGenero(auxiliar.get(key).toString());
                            break;
                        case User.Esquema.TELEFONO:
                            nuevoItemUsuario.setTelefono(Integer.parseInt(auxiliar.get(key).toString()));
                            break;

                    }//fin switch
                }//fin for interno
                listaUsuarios.add(nuevoItemUsuario);

            }//fin for
        }
        milista = findViewById(R.id.lista);
        milista.setHasFixedSize(true);
        manejador = new LinearLayoutManager(this);
        adaptador= new AdaptadorUsuarios(listaUsuarios);

        milista.setLayoutManager(manejador);

        milista.setAdapter(adaptador);
    }
}