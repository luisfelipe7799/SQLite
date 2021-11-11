package com.example.objetos;

public class Asignaturas {


    private String codigo = "";
    private String descripcion = "";
    private int id_asignatura = 0;

    public Asignaturas(){

    }

    public Asignaturas(String codigo, String descripcion,  int id_asignatura) {
        this.codigo = codigo;
        this.descripcion = descripcion;

        this.id_asignatura = id_asignatura;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }
}

