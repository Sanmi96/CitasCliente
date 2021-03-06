package com.sanmi.citasClientes.hibernate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanmi.citasClientes.hibernate.bean.Cita;
import com.sanmi.citasClientes.hibernate.bean.Cliente;

//@WebServlet("/LlistarCitesServlet")
public class LlistarClientsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static String error = null;
    private static String nombre = null;
    private static String primer_apellido = null;
    private static String segundo_apellido = null;
    private Validador validador = new Validador();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (Controlador.isLogin()) {
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("buscar_button") != null) {
                // Filtra els clients
                nombre = request.getParameter("nombre");
                primer_apellido = request.getParameter("primer_apellido");
                segundo_apellido = request.getParameter("segundo_apellido");
            }
            if (!validador.isNameValid(nombre + primer_apellido + segundo_apellido)) {
                request.setAttribute("error", "Car�cteres inv�lidos");
            } else {
                request.setAttribute("error", "");

                List<Cliente> clientes = Controlador.findAllClientsByName(nombre, primer_apellido, segundo_apellido);
                

                request.setAttribute("clientes", clientes);
            }
            
            request.getRequestDispatcher("llistar_clientes.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
        
    }
}
