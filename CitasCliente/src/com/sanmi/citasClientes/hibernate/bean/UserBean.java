package com.sanmi.citasClientes.hibernate.bean;

public class UserBean {

    private int id;
    private String nombre;
    private String password;
    public boolean valid;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean newValid) {
        valid = newValid;
    }
}
