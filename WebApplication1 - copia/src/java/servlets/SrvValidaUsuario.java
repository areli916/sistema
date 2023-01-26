/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import administradorDB.Usuarios; //BIBLIOTECA DE LA CLASE USUARIOS 
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

/**
 *
 * @author 52561
 */
@WebServlet(name = "srvValidaUsuario", urlPatterns = {"/srvValidaUsuario"})
public class SrvValidaUsuario extends HttpServlet {

    Usuarios misUsuarios = new Usuarios(); //CONSTRUCTOR Y OBJETO 
    
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
            String usuario = request.getParameter("usuario");//CREAMOS la variable usuario 
            //con el request(recibir)
            String passw = request.getParameter("passw");
            //String ingresar = request.getParameter("ingresar");
            String nombreProgramador = request.getParameter("nombreProgramador");
            //isEmpty()==ESTA VACIO 
            if(usuario.isEmpty() || passw.isEmpty()){ //EMPTY = vacío 
                response.sendRedirect("index.jsp?error=debes completar todos los datos");
                //nos redirecciona con el error que debemos completar todos los datos. 
            } else if(misUsuarios.validarUsuario(usuario, passw)){
                response.sendRedirect("navegacion.jsp?usuario= "+usuario);//redireccion 
                //para que nos mande a la otra pagina y nos muestra el usuario si es que existe 
            } else {
                response.sendRedirect("index.jsp?error=nombre de usuario o password son invalidos");
                //si no entra,nos mandará el mensaje en la página de logeo, diciendo que 
                //el nombre de usuario o la contraseña son incorrectos. 
            }
            
            //imprime la pagina web que deseemos 
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

