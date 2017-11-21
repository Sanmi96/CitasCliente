package com.sanmi.citasClientes.hibernate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EsborrarServlet extends HttpServlet {

    private static final long serialVersionUID = 3L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("borrar_cita") == null) {
            String cliente_id = request.getParameter("id");
            try {
                Controlador.deleteCliente(Integer.parseInt((cliente_id)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String cita_id = request.getParameter("id");
            try {
                Controlador.deleteCita(Integer.parseInt((cita_id)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("llistar_clientes");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("llistar_clientes");
    }
}