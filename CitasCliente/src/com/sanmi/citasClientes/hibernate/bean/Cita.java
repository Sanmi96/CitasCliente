package com.sanmi.citasClientes.hibernate.bean;

import java.util.Date;

public class Cita {

    int id;
    int cliente_id;
    Date data;
    String dataStr;
    String nota;

    public Cita() {
        super();
    }

    public Cita(int id, int cliente_id, Date data, String nota) {
        super();
        this.id = id;
        this.cliente_id = cliente_id;
        this.data = data;
        this.nota = nota;

    }

    public Cita(int cliente_id, Date data, String nota) {
        this.cliente_id = cliente_id;
        this.data = data;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

}
