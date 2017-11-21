package com.sanmi.citasClientes.hibernate.controller;

import java.util.List;

import com.sanmi.citasClientes.hibernate.bean.Cita;
import com.sanmi.citasClientes.hibernate.bean.Cliente;
import com.sanmi.citasClientes.hibernate.dao.CitaDAO;
import com.sanmi.citasClientes.hibernate.dao.ClienteDAO;
import com.sanmi.citasClientes.hibernate.dao.DataAccessLayerException;
import com.sanmi.citasClientes.hibernate.dao.UserDAO;

public class Controlador {

    private static boolean login = false;
    private static String user = "";
    static CitaDAO citaDAO = new CitaDAO();
    static ClienteDAO clienteDAO = new ClienteDAO();
    static UserDAO userDAO = new UserDAO();

    public static List<Cita> listCites() {

        return citaDAO.findAll();
    }

    public static List<Cliente> listClientes() {
        return clienteDAO.findAll();
    }

    public static List<Cita> findAllCitesOrderedByData(String[] s) {
        return citaDAO.findAllCitesOrderedByData(s);
    }

    public static List<Cliente> findAllClientsByName(String nombre, String primer_apellido, String segundo_apellido) {
        return clienteDAO.findAllClientsByName(nombre, primer_apellido, segundo_apellido);
    }

    public static void deleteCliente(int id) throws DataAccessLayerException {
        clienteDAO.delete(id);
    }

    public static void deleteCita(int id) throws DataAccessLayerException {
        citaDAO.delete(id);
    }

    public static void crearCliente(Cliente cliente) {
        clienteDAO.create(cliente);
    }

    public static void crearCita(Cita cita) {
        citaDAO.create(cita);
    }

    public static void modificarCliente(Cliente cliente) {
        clienteDAO.update(cliente);
    }

    public static void modificarCita(Cita cita) {
        citaDAO.update(cita);
    }

    public static boolean isUserValid(String nombre, String password) {
        return userDAO.isUserValid(nombre, password);
    }

    public static boolean isLogin() {
        return login;
    }

    public static void setLogin(boolean login) {
        Controlador.login = login;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Controlador.user = user;
    }

}
