package com.sanmi.citasClientes.hibernate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanmi.citasClientes.hibernate.bean.Cita;
import com.sanmi.citasClientes.hibernate.bean.Cliente;

public class LlistarCitesServlet extends HttpServlet {

    //private static final long serialVersionUID = 8L;

    private Validador validador = new Validador();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (Controlador.isLogin()) {
            String id = request.getParameter("cliente_id");
            request.setCharacterEncoding("UTF-8");
            List<Cita> citas = Controlador.findCitesOrderedByData(Integer.parseInt(id));
            System.out.println(citas.size());
            request.setAttribute("citas", citas); 
            request.getRequestDispatcher("llistar_citas.jsp").forward(request, response);
        } else response.sendRedirect("login.jsp");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
	
    }
}
