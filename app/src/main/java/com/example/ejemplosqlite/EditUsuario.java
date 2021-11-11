package com.example.ejemplosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sqlite.OperacionesCRUD;
import com.example.sqlite.esquemas.User;

public class EditUsuario extends AppCompatActivity {

    private EditText nom , apep, apem, email, edad, tele,dire;
    private RadioButton mas,fem;
    private int id_user_entrada = 0;
    private int edad_user_entrada = 0;
    private int telefono_user_entrada = 0;
    private String nom_user_entrada = "";
    private String apep_user_entrada = "";
    private String apem_user_entrada = "";
    private String email_user_entrada = "";
    private String dire_user_entrada = "";
    private String genero_user_entrada = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_usuario);

        nom = findViewById(R.id.nomedit);
        nom.setText(nom_user_entrada);

        apep = findViewById(R.id.apepedit);
        apep.setText(apep_user_entrada);

        apem = findViewById(R.id.apemedit);
        apem.setText(apem_user_entrada);

        email = findViewById(R.id.emailedit);
        email.setText(email_user_entrada);

        edad = findViewById(R.id.edadedit);
        edad.setText(""+edad_user_entrada);

        tele = findViewById(R.id.telefonoEdit);
        tele.setText(""+telefono_user_entrada);

        dire = findViewById(R.id.direedit);
        dire.setText(dire_user_entrada);

        mas = findViewById(R.id.masedit);
        fem = findViewById(R.id.femedit);

        if(genero_user_entrada.toUpperCase().equals("MASCULINO")){
            mas.setChecked(true);
            fem.setChecked(false);

        }else{
            mas.setChecked(false);
            fem.setChecked(true);
        }


        if(null != this.getIntent()){
            if(null != this.getIntent().getExtras()){
                Bundle parametrosEntrada = this.getIntent().getExtras();
                id_user_entrada = parametrosEntrada.getInt("id");
                edad_user_entrada = parametrosEntrada.getInt("edad");
                telefono_user_entrada = parametrosEntrada.getInt("telefono");
                nom_user_entrada = parametrosEntrada.getString("nom");
                apep_user_entrada = parametrosEntrada.getString("apepaterno");
                apem_user_entrada = parametrosEntrada.getString("apematerno");
                email_user_entrada = parametrosEntrada.getString("email");
                dire_user_entrada = parametrosEntrada.getString("direccion");
                genero_user_entrada = parametrosEntrada.getString("genero");



            }
        }



        
    }

    public void volver(View v){
        Intent i = new Intent(this,ListaUsuarios.class);
        startActivity(i);
    }
    public void editarUsuario(View v){
        OperacionesCRUD instancia = new OperacionesCRUD(this,"miBD",null,4);
        ContentValues datosNuevosUsuario = new ContentValues();
        datosNuevosUsuario.put("nombre",nom.getText().toString());
        datosNuevosUsuario.put("apePaterno",apep.getText().toString());
        datosNuevosUsuario.put("apeMaterno",apem.getText().toString());
        datosNuevosUsuario.put("email",email.getText().toString());
        datosNuevosUsuario.put("edad",edad.getText().toString());
        datosNuevosUsuario.put("direccion",dire.getText().toString());

        if(mas.isChecked())
            datosNuevosUsuario.put("genero",mas.getText().toString());
        if(fem.isChecked())
            datosNuevosUsuario.put("genero",fem.getText().toString());


        datosNuevosUsuario.put("telefono", tele.getText().toString());

        String condicion = "id_usuario=?";
        String valores[] = {id_user_entrada+""};
        int cantidad_actualizados = 0;
        cantidad_actualizados = instancia.actualizarRegistro(datosNuevosUsuario,
                condicion,valores, User.Esquema.TABLE_NAME);


        if(cantidad_actualizados > 0){
            Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error actualizando usuario", Toast.LENGTH_SHORT).show();
        }
    }






































}