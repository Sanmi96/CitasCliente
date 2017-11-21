package com.sanmi.citasClientes.hibernate.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanmi.citasClientes.hibernate.bean.Cliente;

public class CrearClientServlet extends HttpServlet {

    private static final long serialVersionUID = 4L;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
    boolean repetit = false;
    private Validador validador = new Validador();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("crear_button") != null) {
            request.setCharacterEncoding("UTF-8");
            String nombre = request.getParameter("nombre");
            String primer_apellido = request.getParameter("primer_apellido");
            String segundo_apellido = request.getParameter("segundo_apellido");
            // comprovo que no estigui repetit

            for (Cliente c : Controlador.listClientes()) {
                if (nombre.equals(c.getNombre()) && primer_apellido.equals(c.getPrimer_apellido())
                        && segundo_apellido.equals(c.getSegundo_apellido())) {
                    repetit = true;
                }
            }
            if (repetit) {
                repetit = false;
                request.setAttribute("error", "Ese cliente ya existe");
                request.getRequestDispatcher("llistar_clientes.jsp").forward(request, response);
            }
            // la resta de possibles errors
            else if (nombre.equals("") || primer_apellido.equals("")) {

                request.setAttribute("error", "Campos vacíos");
                request.getRequestDispatcher("llistar_clientes.jsp").forward(request, response);
            } else if (!validador.isNameValid(nombre + primer_apellido + segundo_apellido)) {
                request.setAttribute("error", "Carácteres inválidos");
                request.getRequestDispatcher("llistar_clientes.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "");
                try {
                    Controlador.crearCliente(new Cliente(nombre, primer_apellido, segundo_apellido));
                    response.sendRedirect("llistar_clientes");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            response.sendRedirect("llistar_clientes");
        }

    }
}