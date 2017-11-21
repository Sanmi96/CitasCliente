package com.sanmi.citasClientes.hibernate.bean;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    int id;
    String nombre;
    String primer_apellido;
    String segundo_apellido;
    List<Cita> cites = new ArrayList<Cita>();

    public Cliente(int id, String nombre, String primer_apellido, String segundo_apellido) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
    }

    public Cliente(String nombre, String primer_apellido, String segundo_apellido) {
        super();
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
    }

    public Cliente() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public List<Cita> getCites() {
        return cites;
    }

    public void deleteCites() {
        this.cites.clear();
    }

    public void addCita(Cita cita) {
        this.cites.add(cita);
    }

}
