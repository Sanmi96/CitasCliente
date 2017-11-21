package com.sanmi.citasClientes.hibernate.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanmi.citasClientes.hibernate.bean.Cita;

public class CrearCitaServlet extends HttpServlet {

    private static final long serialVersionUID = 2L;

    private static String cliente_id = null;
    private static String nota = null;
    private static String data = null;
    private Validador validador = new Validador();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (Controlador.isLogin()) {
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("crear_btn") != null) {
                nota = request.getParameter("nota");
                data = request.getParameter("data");
                request.setAttribute("cliente_id", cliente_id);
                request.setAttribute("nota", nota);
                request.setAttribute("data", data);
                request.setAttribute("nombre", request.getParameter("nombre"));
                if (nota.equals("") || data.equals("")) {
                    request.setAttribute("error", "Campos vacíos");
                    request.getRequestDispatcher("crear_cita.jsp").forward(request, response);
                } else if (!validador.isNotaValid(nota)) {
                    request.setAttribute("error", "Carácteres inválidos");
                    request.getRequestDispatcher("crear_cita.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "");
                    try {
                        Date parsedData = new SimpleDateFormat("yyyy-MM-dd").parse(data);
                        System.out.println(parsedData);
                        Controlador.crearCita(new Cita(Integer.parseInt(cliente_id), parsedData, nota));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    response.sendRedirect("llistar_clientes");
                }
            } else {
                response.sendRedirect("llistar_clientes");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("crear_button") == null) {
            response.sendRedirect("llistar_clientes");
        } else {
            cliente_id = request.getParameter("cliente_id");
            request.setAttribute("nombre", request.getParameter("nombre"));
            request.getRequestDispatcher("crear_cita.jsp").forward(request, response);
        }

    }
}