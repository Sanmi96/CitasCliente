package com.sanmi.citasClientes.hibernate.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        try {
            String nombre = request.getParameter("nombre");
            String password = request.getParameter("password");

            if (nombre.equals("") || password.equals("")) {
                request.setAttribute("error", "Campos vacícos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else if (Controlador.isUserValid(nombre, password)) {
                Controlador.setLogin(true);
                Controlador.setUser(nombre);
                request.setAttribute("error", "");
                response.sendRedirect("llistar_clientes"); // logged-in page
            }

            else {
                request.setAttribute("error", "Nombre de usuario o contraseña incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }

        catch (Throwable theException) {
            System.out.println(theException);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        response.sendRedirect("login.jsp");
    }

}