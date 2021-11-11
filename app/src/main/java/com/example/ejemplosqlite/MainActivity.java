package com.example.ejemplosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sqlite.OperacionesCRUD;
import com.example.sqlite.esquemas.User;

public class MainActivity extends AppCompatActivity {

    Button irAsignatura;
    EditText nombre;
    EditText apellidoP;
    EditText apellidoM;
    EditText email;
    EditText direccion;
    EditText edad;
    EditText telefono;
    RadioButton femenino;
    RadioButton masculino;

    Button irLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        irAsignatura = (Button) findViewById(R.id.pasarAsignaturas);
        nombre = (EditText) findViewById(R.id.nomedit);
        apellidoP = (EditText) findViewById(R.id.apepedit);
        apellidoM = (EditText) findViewById(R.id.apeMaterno);

        email = (EditText) findViewById(R.id.email);
        direccion = (EditText) findViewById(R.id.direccion);
        edad = (EditText) findViewById(R.id.edad);
        telefono = (EditText) findViewById(R.id.telefono);
        femenino = (RadioButton) findViewById(R.id.generoFemenino);
        masculino = (RadioButton) findViewById(R.id.generoMasculino);
        irLista = (Button) findViewById(R.id.irLista);

        irLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListaUsuarios.class);
                startActivity(intent);
            }
        });

        irAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Asignatura.class);
                startActivity(intent);
            }
        });

    }
    public void insert (View view){

        OperacionesCRUD oper = new OperacionesCRUD(this,
                "miBD",
                null,
                4);

        ContentValues datosUsuario = new ContentValues();
        datosUsuario.put(User.Esquema.NOMBRE, nombre.getText().toString());
        datosUsuario.put(User.Esquema.APEPATERNO, apellidoP.getText().toString());
        datosUsuario.put(User.Esquema.APEMATERNO, apellidoM.getText().toString());
        datosUsuario.put(User.Esquema.DIRECCION, direccion.getText().toString());
        datosUsuario.put(User.Esquema.EMAIL, email.getText().toString());
        datosUsuario.put(User.Esquema.EDAD, edad.getText().toString());

        if (femenino.isChecked() == true){
            datosUsuario.put(User.Esquema.GENERO, femenino.getText().toString());
        }
        else{
            datosUsuario.put(User.Esquema.GENERO, masculino.getText().toString());
        }

        datosUsuario.put(User.Esquema.TELEFONO, telefono.getText().toString());

        long id_user_insertado = 0;
        id_user_insertado = oper.insertTabla(datosUsuario,User.Esquema.TABLE_NAME);
        Toast.makeText(this,"id de usuario insertado: "+ id_user_insertado,Toast.LENGTH_LONG).show();



        //System.out.println("retorno de insert asignatura: "+ id_asignatura_insertado);
        //Toast.makeText(this,"retorno de insert asignatura: "+ id_asignatura_insertado,Toast.LENGTH_LONG).show();

    }




}