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

import com.example.adaptadores.AdaptadorAsignaturas;
import com.example.adaptadores.AdaptadorUsuarios;
import com.example.objetos.Asignaturas;
import com.example.objetos.Usuario;
import com.example.sqlite.OperacionesCRUD;
import com.example.sqlite.esquemas.Asignatura;
import com.example.sqlite.esquemas.User;

import java.util.ArrayList;
import java.util.List;

public class ListaAsignaturas extends AppCompatActivity {

    private RecyclerView milista;
    private RecyclerView.LayoutManager manejador;
    private RecyclerView.Adapter adaptador;

    Button irAgregarAsignatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_asignaturas);
        irAgregarAsignatura = (Button) findViewById(R.id.agregarAsig);

        irAgregarAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaAsignaturas.this, com.example.ejemplosqlite.Asignatura.class);
                startActivity(intent);
            }
        });


        OperacionesCRUD instancia = new OperacionesCRUD(this,"miBD",null,4);

        String[] columnasAsignatura = Asignatura.Esquema.ALLCOLUMNAS;
        String condicion = "";
        String[] valCondicion = {};

        List<ContentValues> asignaturasObtenidos = instancia.obtenerDatos(columnasAsignatura, condicion,
                valCondicion,"asignatura");


        ArrayList<Asignaturas> listaAsignaturas = new ArrayList<>();
        if(asignaturasObtenidos == null){
            Toast.makeText(this, "No se  obtuvieron asignaturas desde base de datos", Toast.LENGTH_SHORT).show();

        }else{
            for(int i=0; i < asignaturasObtenidos.size(); i++){
                ContentValues auxiliar = asignaturasObtenidos.get(i);
                System.out.println("Entra a for");
                Asignaturas nuevoItemAsignatura = new Asignaturas();
                for(String key: auxiliar.keySet()){
                    switch (key.toString()){
                        case Asignatura.Esquema.ID:
                            nuevoItemAsignatura.setId_asignatura(Integer.parseInt(auxiliar.get(key).toString()));
                            break;
                        case Asignatura.Esquema.CODIGO:
                            nuevoItemAsignatura.setCodigo(auxiliar.get(key).toString());
                            break;
                        case Asignatura.Esquema.DESCRIPCION:
                            nuevoItemAsignatura.setDescripcion(auxiliar.get(key).toString());
                            break;


                    }//fin switch
                }//fin for interno
                listaAsignaturas.add(nuevoItemAsignatura);

            }//fin for
        }
        milista = findViewById(R.id.listaasig);
        milista.setHasFixedSize(true);
        manejador = new LinearLayoutManager(this);
        adaptador= new AdaptadorAsignaturas(listaAsignaturas);

        milista.setLayoutManager(manejador);

        milista.setAdapter(adaptador);

    }
}