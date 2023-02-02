
package servlets;

import administradorDB.Alumnos;
import administradorDB.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author 52561
 */
@WebServlet(name = "SrvValidaRegistroAlumno", urlPatterns = {"/SrvValidaRegistroAlumno"})
public class SrvValidaRegistroAlumno extends HttpServlet {
    Alumnos misAlumnos = new Alumnos();
    Usuarios misUsuarios = new Usuarios();

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
            String matricula = request.getParameter("matricula"); 
            String correo = request.getParameter("correo");
            String fnacimiento = request.getParameter("fnacimiento");
            String carrera = request.getParameter("carrera");
            String nombre = request.getParameter("nombre");
            String password = request.getParameter("password");
            
            
            // patrn: abc
            // cadena: El abcdario
            // match: true si pattern est en cadena, false en caso contrario
            Pattern pattern= Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
            Matcher matcher= pattern.matcher(fnacimiento);
            
            /*boolean bndRegistro=true;
            if(!correo){bndRegistro=false}
            if(!fnacimiento){bndRegistro=false}
            if(!otracosa){bndRegistro=false}
            if(bndRegistro)*/
                
            if(matcher.matches()){
                //validamos que los 4 campos esten llenos 
                if(matricula.isEmpty() || correo.isEmpty() || fnacimiento.isEmpty() || carrera.isEmpty()){
                    //? primer parametro || & segundo parametron || & tercer paramtero y mas...
                    response.sendRedirect("registraralumno.jsp?error=Debes llenar todos los campos"
                            + "&matricula="+matricula+"&correo="+correo+"&fnacimiento="+fnacimiento+"&carrera="+carrera);

                //verificamos la matricula si ya existe, manda un error  (se entiende que si existe el alumno, existe el usuario, porque están relacionados) 
                }else if(misAlumnos.existeAlumno(matricula)){
                    response.sendRedirect("registraralumno.jsp?error=La matricula que proporcionaste "
                            + "ya esta en el sistema"
                            + "&matricula="+matricula+"&correo="+correo+"&fnacimiento="+fnacimiento+"&carrera="+carrera);
                // Si falla alguna inserción, se va a mandar el error
                }else if(!misUsuarios.insertarUsuario(correo, nombre, password) || !misAlumnos.insertarAlumno(matricula, correo, fnacimiento, carrera)){
                    response.sendRedirect("registraralumno.jsp?error=Error a la inserccion, fallo en la "
                            + "comunicacion con la base de datos"
                            + "&matricula="+matricula+"&correo="+correo+"&fnacimiento="+fnacimiento+"&carrera="+carrera+"&nombre="+nombre+"&password="+password);
                //}else if(!fnacimiento.equals(String.format("%.10s",fnacimiento))){ //== !=, fnacimiento.equals(String.format.....) SE
                    //Se rompia por operadores
                }else{
                    response.sendRedirect("bienvenida.jsp?mensaje=Alumno registrado exitosamente!!!");
                }
            }
            else{
                response.sendRedirect("registraralumno.jsp?error=Escribe bien la fecha");
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
