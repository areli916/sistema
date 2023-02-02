/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import administradorDB.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "SrvValidarRegistroUsuario", urlPatterns = {"/SrvValidarRegistroUsuario"})
public class SrvValidarRegistroUsuario extends HttpServlet {
Usuarios misusuario=new Usuarios();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String correo=request.getParameter("correo");
            String nombre=request.getParameter("nombre");
            String pw=request.getParameter("pw");
            if(correo.isEmpty()||nombre.isEmpty()||pw.isEmpty()){
                 response.sendRedirect("registrarusuario.jsp?error=Debes llenar todos los campos"+
                         "&correo"+correo+"&nombre"+nombre+"&pw"+pw);
            }else if(misusuario.existeUsuario(correo)){
                 response.sendRedirect("registrarusuario.jsp?error=El correo que proporcionaste "
                            + "ya esta en el sistema"
                            + "&correo="+correo+"&nombre="+nombre+"&pw"+pw);
                
            }else if(!misusuario.insertarUsuario(correo, nombre, pw)){
                 response.sendRedirect("registrarusuario.jsp?error=Error a la inserccion, fallo en la "
                            + "comunicacion con la base de datos"
                                + "&correo="+correo+"&nombecarrera="+nombre+"&pw"+pw);
            }else{
                response.sendRedirect("bienvenida.jsp?mensaje=usuario registrado exitosamente!!!");
                
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
