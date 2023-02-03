package servlets;
import administradorDB.Alumnos;
import administradorDB.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SrvProcesosAlumnos", urlPatterns = {"/SrvProcesosAlumnos"})
public class SrvProcesosAlumnos extends HttpServlet { 
    Alumnos misAlumnos = new Alumnos(); 
    Usuarios misUsuarios= new Usuarios();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String btnAlumnos = request.getParameter("btnAlumnos");
            String buscar = request.getParameter("buscar");
            String matricula= request.getParameter("matricula");
            String correo= request.getParameter("correo");
            String correoAnterior= request.getParameter("correoAnterior");
            String nombre= request.getParameter("nombre");
            String password= request.getParameter("password");
            
            System.out.println("MAPA DE ENTRADAS DE FORMULARIO (SrvProcesosAlumnos)");
            Map<String, String[]> m= request.getParameterMap();
            for(Entry<String, String[]> entry: m.entrySet() ){
                System.out.println(entry.getKey()+": "+entry.getValue()[0]);
            }
            System.out.println("**************************");
            
            
            if(btnAlumnos.equals("Buscar")){ 
                if(buscar.isEmpty()){
                    response.sendRedirect("bienvenida.jsp"); 
                } else{
                    response.sendRedirect("bienvenida.jsp?alumnosEncontrados="+buscar); } 
            } else if (btnAlumnos.equals("Inactivos")){ 
                if(buscar.isEmpty()){
                    response.sendRedirect("Inactivos.jsp");
                } else{
                    response.sendRedirect("Inactivos.jsp?busquedadeinactivosexitosa="+buscar); } 
            } else if (btnAlumnos.equals("eliminar")) {
                if ( misAlumnos.eliminarAlumno(matricula) && misUsuarios.eliminarUsuario(correo) ){
                    response.sendRedirect("bienvenida.jsp?mensaje=Se elimino correctamente"); 
                } else {
                    response.sendRedirect("bienvenida.jsp?error=Error de comunicacion con la base de datos");  }
            } else if (btnAlumnos.equals("reintegrar")){
                
                if(misAlumnos.reintegrarAlumno(matricula) && misUsuarios.reintegrarUsuario(correo)){
                    response.sendRedirect("Inactivos.jsp?mensaje=Se reintegro correctamente"); 
                } else { 
                    response.sendRedirect("Inactivos.jsp?error=Error de comunicacion con la base de datos"); 
                }
            } else if (btnAlumnos.equals("modificar")) {
                //Pendiente, modificar correo por la dependencia de llave foranea
                boolean modifAlumnos= misAlumnos.modificarAlumno(                        
                        matricula, 
                        correo, 
                        correoAnterior,
                        request.getParameter("fnacimiento"), 
                        request.getParameter("carrera")
                    );
                boolean modifUsuarios= misUsuarios.modificarUsuario(correo, correoAnterior, nombre, password);
                
                if (modifAlumnos && modifUsuarios){
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