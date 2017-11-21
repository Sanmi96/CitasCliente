package com.sanmi.citasClientes.hibernate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

    private static String cliente_id = null;
    private static String nota = null;
    private static String data = null;
    private Validador validador = new Validador();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Controlador.setLogin(false);
        Controlador.setUser("");
        response.sendRedirect("llistar_clientes");
    }
}