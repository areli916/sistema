
package servlets;

import administradorDB.Carreras;
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
@WebServlet(name = "SrvProcesosCarreras", urlPatterns = {"/SrvProcesosCarreras"})
public class SrvProcesosCarreras extends HttpServlet {
    Carreras misCarreras=new Carreras();
    

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
            String btnCarreras = request.getParameter("btnCarrera");
            String buscar = request.getParameter("buscar");
            String clave= request.getParameter("clave");
            
            System.out.println("MAPA DE ENTRADAS DE FORMULARIO (SrvProcesosCarreras)");
            Map<String, String[]> m= request.getParameterMap();
            for(Map.Entry<String, String[]> entry: m.entrySet() ){
                System.out.println(entry.getKey()+": "+entry.getValue()[0]);
            }
            System.out.println("**************************");
            
            if(btnCarreras.equals("Buscar")){ 
                if(buscar.isEmpty()){
                    response.sendRedirect("catalogocarreras.jsp"); 
                } else{
                    response.sendRedirect("catalogocarreras.jsp?carrerasEncontradas="+buscar);
                    System.out.println("la carrera a buscar es"+buscar );
                } 
        }else if (btnCarreras.equals("Inactivas")){ 
                if(buscar.isEmpty()){
                    response.sendRedirect("carrerasinactivas.jsp");
                } else{
                    response.sendRedirect("carrerasinactivas.jsp?busquedadeinactivasexitosa="+buscar); } 
            } else if (btnCarreras.equals("eliminar")) {
                if (misCarreras.eliminarCarrera(clave)){
                    response.sendRedirect("catalogocarreras.jsp?mensaje=Se elimino correctamente"); 
                } else {
                    response.sendRedirect("catalogocarreras.jsp?error=Error de comunicacion con la base de datos");  }
            } else if (btnCarreras.equals("reintegrar")){
                
                if(misCarreras.reintegrarCarrera(clave)){
                    response.sendRedirect("catalogocarreras.jsp?mensaje=Se reintegro correctamente"); 
                } else { 
                    response.sendRedirect("catalogocarreras.jsp?error=Error de comunicacion con la base de datos"); 
                }
            } else if (btnCarreras.equals("modificar")) {
                if (misCarreras.modificarCarrera(clave,
                        request.getParameter("nombrecarrera")
                         )){
                    response.sendRedirect("catalogocarreras.jsp?mensaje=Se modifico correctamente"); 
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
