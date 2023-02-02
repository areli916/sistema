/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import administradorDB.Carreras;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Windows 10
 */
@WebServlet(name = "SrvValidarRegistroCarrera", urlPatterns = {"/SrvValidarRegistroCarrera"})
public class SrvValidarRegistroCarrera extends HttpServlet {
    Carreras miscarreras=new Carreras();

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
        //try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            
            String clave=request.getParameter("clave");
            String nombrecarrera=request.getParameter("nombrecarrera");
            if (clave.isEmpty()||nombrecarrera.isEmpty()) {
                response.sendRedirect("registrarcarrera.jsp?error=Debes llenar todos los campos"+
                        "&clave="+clave+"&nombrecarrera="+nombrecarrera);
                
            }else if(miscarreras.existeCarrera(clave)){
                  response.sendRedirect("registrarcarrera.jsp?error=La matricula que proporcionaste "
                            + "ya esta en el sistema"
                            + "&clave="+clave+"&nombrecarrera="+nombrecarrera);
            }else if(!miscarreras.insertarCarrera(clave, nombrecarrera)){
                response.sendRedirect("registrarcarrera.jsp?error=Error a la inserccion, fallo en la "
                            + "comunicacion con la base de datos"
                                + "&clave="+clave+"&nombecarrera="+nombrecarrera);
            }else{
                    response.sendRedirect("catalogocarreras.jsp?mensaje=carrera registrada exitosamente!!!");
            }
        //}
        //catch(Exception e){
                
                //response.sendRedirect("registraralumno.jsp?error=Escribe bien la fecha");
            
        //}
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
