package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Path;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.sqlite.esquemas.Asignatura;
import com.example.sqlite.esquemas.Calificacion;
import com.example.sqlite.esquemas.User;
import com.example.sqlite.esquemas.UsrAsig;

import java.util.ArrayList;
import java.util.List;


public class OperacionesCRUD extends SQLiteOpenHelper {

        public OperacionesCRUD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(User.Esquema.CREA_TABLA_USUARIO);
            sqLiteDatabase.execSQL(Asignatura.Esquema.CREA_TABLA_ASIGNATURA);
            sqLiteDatabase.execSQL(UsrAsig.Esquema.CREA_TABLA_USUARIO_ASIGNATURA);
            sqLiteDatabase.execSQL(Calificacion.Esquema.CREA_TABLA_CALIFICACION);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(User.Esquema.BORRA_TABLA_USUARIO);
            sqLiteDatabase.execSQL(Asignatura.Esquema.BORRA_TABLA_ASIGNATURA);
            sqLiteDatabase.execSQL(UsrAsig.Esquema.BORRA_TABLA_USUARIO_ASIGNATURA);
            sqLiteDatabase.execSQL(Calificacion.Esquema.BORRA_TABLA_CALIFICACION);
            onCreate(sqLiteDatabase);

        }




    //metodo para insertar en cualquier tabla
     public long insertTabla(ContentValues columnas_valores_insertar, String nombreTabla) {
         long id_registro_insertado = 0;
         try {
            SQLiteDatabase db = this.getWritableDatabase();
            id_registro_insertado = db.insert(nombreTabla, null, columnas_valores_insertar);
        } catch (Exception e) {
            System.out.println("ERROR metodo insertar: " + e.getMessage());

        }
        return id_registro_insertado;

     }


     public int borrarRegistro(String nombre_tabla, String condicion, String[] val_condicion){

            int registros_eliminados = 0;
            try{
                SQLiteDatabase db = this.getWritableDatabase();
                registros_eliminados = db.delete(nombre_tabla, condicion, val_condicion);

            }catch (Exception e){
                System.out.println("Error metodo borrar: " + e.getMessage());
            }
            return registros_eliminados;
     }

     public int actualizarRegistro(ContentValues columna_valores, String condicion,
                                   String[] condicion_valores, String nombre_tabla){
            int cantidad_actualizados = 0;

            try{
                SQLiteDatabase db = this.getWritableDatabase();
                cantidad_actualizados = db.update(
                        nombre_tabla,
                        columna_valores,
                        condicion,
                        condicion_valores);
            }catch(Exception e){
                System.out.println("Error metodo actualizar registros :" + e.getMessage());
            }
            return cantidad_actualizados;
     }



        public List<ContentValues> obtenerDatos(String columnasObtener[], String columnasFiltro,
                                                String valoresFiltro[], String nombreTabla){
            Cursor resultado = null;

            try {
                SQLiteDatabase db = this.getWritableDatabase();
                List<ContentValues> listaRegistrosObtenidos = new ArrayList<ContentValues>();

                resultado = db.query(
                        nombreTabla, columnasObtener, columnasFiltro, valoresFiltro,
                        null,
                        null,
                        null

                );

                if (null != resultado){
                    //recorrer cursos obtenido desde base de datos

                    resultado.moveToFirst();

                    while (resultado.isAfterLast()== false){
                        ContentValues auxiliar = new ContentValues();
                        for (int i=0; i<resultado.getColumnCount(); i++)
                        {
                            auxiliar.put(resultado.getColumnName(i), resultado.getString(i));

                        }//fin for
                        listaRegistrosObtenidos.add(auxiliar);
                        resultado.moveToNext();
                    }//fin while
                }
                resultado.close();
                return listaRegistrosObtenidos;

            }catch (Exception e){
                System.out.println("Error metodo obtener registros : "+ e.getMessage());
            }
            return null;
        }




    }




