/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import administradorDB.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "SrvProcesosUsuarios", urlPatterns = {"/SrvProcesosUsuarios"})
public class SrvProcesosUsuarios extends HttpServlet {
    Usuarios misusuarios=new Usuarios();

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
           String btnUsuarios=request.getParameter("btnUsuarios");
           String buscar=request.getParameter("buscar");
           String correo=request.getParameter("correo");
           System.out.println("MAPA DE ENTRADAS DE FORMULARIO (SrvProcesosUsuarios)");
            Map<String, String[]> m= request.getParameterMap();
            for(Map.Entry<String, String[]> entry: m.entrySet() ){
                System.out.println(entry.getKey()+": "+entry.getValue()[0]);
            }
            System.out.println("**************************");
            
           if(btnUsuarios.equals("Buscar")){ 
                if(buscar.isEmpty()){
                    response.sendRedirect("catalogousuarios.jsp"); 
                } else{
                    response.sendRedirect("catalogousuarios.jsp?usuariosEncontrados="+buscar);
                    System.out.println("el usuario a buscar es"+buscar );
                } 
        }else if (btnUsuarios.equals("Inactivos")){ 
                if(buscar.isEmpty()){
                    response.sendRedirect("InactivosUsuarios.jsp");
                } else{
                    response.sendRedirect("InactivosUsuarios.jsp?busquedadeinactivosexitosa="+buscar); } 
            } else if (btnUsuarios.equals("eliminar")) {
                if (misusuarios.eliminarUsuario(correo)){
                    response.sendRedirect("bienvenida.jsp?mensaje=Se elimino correctamente"); 
                } else {
                    response.sendRedirect("bienvenida.jsp?error=Error de comunicacion con la base de datos");  }
            } else if (btnUsuarios.equals("reintegrar")){
                
                if(misusuarios.reintegrarUsuario(correo)){
                    response.sendRedirect("bienvenida.jsp?mensaje=Se reintegro correctamente"); 
                } else { 
                    response.sendRedirect("bienvenida.jsp?error=Error de comunicacion con la base de datos"); 
                }
            } else if (btnUsuarios.equals("modificar")) {
                System.out.println("el correo"+""+correo );
                if (misusuarios.modificarUsuario(correo,
                       
                        request.getParameter("nombre"),
                        request.getParameter("pw")
                       
                         )){
                    response.sendRedirect("bienvenida.jsp?mensaje=Se modifico correctamente"); 
                } else {
                    response.sendRedirect("bienvenida.jsp?error=Error de comunicacion con la base de datos");  }
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
