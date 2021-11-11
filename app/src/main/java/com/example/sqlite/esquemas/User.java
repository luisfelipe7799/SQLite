package com.example.sqlite.esquemas;


import android.provider.BaseColumns;

public class User {

    public static abstract class Esquema implements BaseColumns{
        public static final String TABLE_NAME = "usuario";
        public static final String ID = "id_usuario";
        public static final String NOMBRE = "nombre";
        public static final String APEPATERNO = "apePaterno";
        public static final String APEMATERNO = "apeMaterno";
        public static final String EMAIL = "email";
        public static final String EDAD = "edad";
        public static final String DIRECCION = "direccion";
        public static final String GENERO = "genero";
        public static final String TELEFONO = "telefono";


        public static String[] ALLCOLUMNAS = {ID,NOMBRE,APEMATERNO,APEMATERNO,EMAIL,EDAD,DIRECCION,GENERO,TELEFONO};
        //SCRIPT PARA CREACION DE TABLA
        public static final String CREA_TABLA_USUARIO = " CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOMBRE + " TEXT," +
                APEPATERNO + " TEXT," +
                APEMATERNO + " TEXT," +
                DIRECCION + " TEXT," +
                EDAD + " INTEGER," +
                EMAIL + " TEXT," +
                TELEFONO + " INTEGER," +
                GENERO + " TEXT)" ;

        //script para borrar tabla
        public static  final  String BORRA_TABLA_USUARIO =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
