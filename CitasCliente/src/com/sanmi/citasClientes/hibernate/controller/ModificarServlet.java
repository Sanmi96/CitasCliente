package com.sanmi.citasClientes.hibernate.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanmi.citasClientes.hibernate.bean.Cita;
import com.sanmi.citasClientes.hibernate.bean.Cliente;

public class ModificarServlet extends HttpServlet {

    private static final long serialVersionUID = 6L;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static String id = null;
    private static String nombre = null;
    private static String primer_apellido = null;
    private static String segundo_apellido = null;
    private static String cliente_id = null;
    private static String nota = null;
    private static String data = null;
    private Validador validador = new Validador();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (Controlador.isLogin()) {
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("modificar_cita") == null && request.getParameter("modificar_cliente") == null) {
                response.sendRedirect("llistar_clientes");
            } else if (request.getParameter("modificar_cliente") != null) {
                id = request.getParameter("id");
                nombre = request.getParameter("nombre");
                primer_apellido = request.getParameter("primer_apellido");
                segundo_apellido = request.getParameter("segundo_apellido");
                request.setAttribute("id", id);
                request.setAttribute("nombre", nombre);
                request.setAttribute("primer_apellido", primer_apellido);
                request.setAttribute("segundo_apellido", segundo_apellido);
                boolean repetit = false;
                for (Cliente c : Controlador.listClientes()) {
                    if (nombre.equals(c.getNombre()) && primer_apellido.equals(c.getPrimer_apellido())
                            && segundo_apellido.equals(c.getSegundo_apellido()) && !id.equals(Integer.toString(c.getId()))) {
                        repetit = true;
                    }
                }
                // Que no estigui repetit
                if (repetit) {
                    request.setAttribute("error", "Ese cliente ya existe");
                    request.getRequestDispatcher("modificar_cliente.jsp").forward(request, response);
                }
                // Control d'errors
                else if (nombre.equals("") || primer_apellido.equals("")) {

                    request.setAttribute("error", "Campos vacíos");
                    request.getRequestDispatcher("modificar_cliente.jsp").forward(request, response);
                } else if (!validador.isNameValid(nombre + primer_apellido + segundo_apellido)) {
                    request.setAttribute("error", "Carácteres inválidos");
                    request.getRequestDispatcher("modificar_cliente.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "");

                    try {
                        Controlador.modificarCliente(
                                new Cliente(Integer.parseInt(id), nombre, primer_apellido, segundo_apellido));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    response.sendRedirect("llistar_clientes");
                }
            } else {
                id = request.getParameter("id");
                cliente_id = request.getParameter("cliente_id");
                nota = request.getParameter("nota");
                data = request.getParameter("data");
                request.setAttribute("id", id);
                request.setAttribute("cliente_id", cliente_id);
                request.setAttribute("nota", nota);
                request.setAttribute("data", data);
                Date parsedData = null;
                // Control d'errors
                if (nota.equals("") || data.equals("")) {
                    request.setAttribute("error", "Campos vacíos");
                    request.getRequestDispatcher("modificar_cita.jsp").forward(request, response);
                } else if (!validador.isNotaValid(nota)) {
                    request.setAttribute("error", "Carácteres inválidos");
                    request.getRequestDispatcher("modificar_cita.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "");
                    try {
                        parsedData = sdf.parse(data);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    try {

                        Controlador.modificarCita(
                                new Cita(Integer.parseInt(id), Integer.parseInt(cliente_id), parsedData, nota));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    response.sendRedirect("llistar_clientes");
                }

            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("modificar_cita") == null && request.getParameter("modificar_cliente") == null) {
            response.sendRedirect("llistar_clientes");
        } else if (request.getParameter("modificar_cliente") != null) {
            id = request.getParameter("id");
            nombre = request.getParameter("nombre");
            primer_apellido = request.getParameter("primer_apellido");
            segundo_apellido = request.getParameter("segundo_apellido");
            request.setAttribute("id", id);
            request.setAttribute("nombre", nombre);
            request.setAttribute("primer_apellido", primer_apellido);
            request.setAttribute("segundo_apellido", segundo_apellido);
            request.getRequestDispatcher("modificar_cliente.jsp").forward(request, response);

        } else {
            id = request.getParameter("id");
            cliente_id = request.getParameter("cliente_id");
            nota = request.getParameter("nota");
            data = request.getParameter("data");
            data = data.replaceAll(" ", "T");
            request.setAttribute("id", id);
            request.setAttribute("cliente_id", cliente_id);
            request.setAttribute("nota", nota);
            request.setAttribute("data", data);
            System.out.println(data);
            request.getRequestDispatcher("modificar_cita.jsp").forward(request, response);
        }

    }
}