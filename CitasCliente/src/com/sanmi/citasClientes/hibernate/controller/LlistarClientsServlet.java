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
                request.setAttribute("error", "Carácteres inválidos");
            } else {
                request.setAttribute("error", "");

                List<Cliente> clientes = Controlador.findAllClientsByName(nombre, primer_apellido, segundo_apellido);
                if (!clientes.isEmpty()) {
                    String[] ids = new String[clientes.size()];
                    for (int i = 0; i < clientes.size(); i++) {
                        ids[i] = Integer.toString(clientes.get(i).getId());
                    }
                    // Selecciona nomÃ©s les dels clients que toca
                    List<Cita> cites = Controlador.findAllCitesOrderedByData(ids);
                    // Assigna les cites a cada client
                    for (int i = 0; i < clientes.size(); i++) {
                        clientes.get(i).deleteCites();
                        for (int y = 0; y < cites.size(); y++) {

                            if (clientes.get(i).getId() == cites.get(y).getCliente_id()) {
                                clientes.get(i).addCita(cites.get(y));
                            }
                        }
                    }
                }

                request.setAttribute("clientes", clientes);
            }

            request.getRequestDispatcher("llistar_clientes.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
